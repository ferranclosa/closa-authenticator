package com.closa.authentication.services;

import com.closa.authentication.dao.UConRepository;
import com.closa.authentication.dto.JwtResponse;
import com.closa.authentication.dto.UserDetailsiDTO;
import com.closa.authentication.dto.UserDetailsoDTO;
import com.closa.authentication.model.UserConnection;
import com.closa.authentication.model.UserRole;
import com.closa.global.security.model.User;
import com.closa.global.throwables.AppException;
import com.closa.global.throwables.MessageCode;
import com.closa.global.throwables.exceptions.InvalidCredentialsException;
import com.closa.global.throwables.exceptions.ItemNotFoundException;
import com.closa.global.throwables.exceptions.UserNotActiveException;
import com.closa.global.throwables.exceptions.UserNotFoundException;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UConRepository uConRepository;

    @Autowired
    UserDetailsService userDetailsService;


    public UserDetailsoDTO dealProvideUserDetails(UserDetailsiDTO iDto) throws AppException {
        UserDetailsoDTO oDTO = new UserDetailsoDTO();
        Optional<User> optionalUser = uConRepository.provideUserDetails(iDto.getUserName());
        if (optionalUser.isPresent()) {
            oDTO.setUser(optionalUser.get());
            oDTO.setUserName();
            oDTO.setPassword();
            oDTO.setPasswordExpired();
            oDTO.setEnabled();
            oDTO.setExpired();
            oDTO.setBlocked();
            oDTO.setAuthorities();
            oDTO.setRoles();
            oDTO.setReturnCode(MessageCode.APP0000.getrCode());
            oDTO.setReturnLabel(MessageCode.APP0000.getmMsg());
        } else {
            throw new ItemNotFoundException(iDto.getUserName());
        }
        return oDTO;
    }
    public JwtResponse authenticate(String username, String password) throws AppException, Exception {
        JwtResponse oDto = new JwtResponse();

        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new UserNotActiveException(username);
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException(username);
        } catch (Exception e){
            throw new UserNotFoundException(username);
        }
        Optional<UserConnection> optionalUserConnection = uConRepository.findByConnectionId(username);
        if(optionalUserConnection.isPresent()) {
            optionalUserConnection.get().setConnected(true);
            optionalUserConnection.get().setLastTimeConnected(LocalDateTime.now());
            uConRepository.save(optionalUserConnection.get());
            for (UserRole one : optionalUserConnection.get().getConnectionUser().getUserRoles()) {
                oDto.getLevels().add(one.getRoleCodeNumber());
            }
        }
        return oDto;
    }

    public void dealWithSignout(UserDetailsiDTO iDto) {

        Optional<UserConnection> optionalUserConnection = uConRepository.findByConnectionId(iDto.getUserName());
        if(optionalUserConnection.isPresent()){
            optionalUserConnection.get().setConnected(false);
            optionalUserConnection.get().setLastTimeConnected(LocalDateTime.now());
            uConRepository.save(optionalUserConnection.get());
        }

    }
}
