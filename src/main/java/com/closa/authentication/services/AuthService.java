package com.closa.authentication.services;

import com.closa.authentication.dao.*;
import com.closa.authentication.dto.*;
import com.closa.authentication.model.*;
import com.closa.global.security.model.User;
import com.closa.global.status.model.enums.Status;
import com.closa.global.status.util.StatusHelper;
import com.closa.global.throwables.AppException;
import com.closa.global.throwables.MessageCode;
import com.closa.global.throwables.exceptions.*;
import com.closa.global.validators.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UConRepository uConRepository;

/*
    @Autowired
    UserDetailsService userDetailsService;
*/

    @Autowired
    URolRepository rolRepository;
    @Autowired
    UAddRepository addRepository;
    @Autowired
    UUsrRepository usrRepository;

    @Autowired
    UCrdRepository crdRepository;

    @Autowired
    StatusHelper statusHelper;

    @Autowired
    PasswordEncoder passwordEncoder;


    public UserDetailsoDTO dealProvideUserDetails(UserDetailsiDTO iDto) throws AppException {
        UserDetailsoDTO oDTO = new UserDetailsoDTO();
        Validators.validate(iDto);
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
        } catch (Exception e) {
            throw new UserNotFoundException(username);
        }
        Optional<UserConnection> optionalUserConnection = uConRepository.findByConnectionId(username);
        if (optionalUserConnection.isPresent()) {
            optionalUserConnection.get().setConnected(true);
            optionalUserConnection.get().setLastTimeConnected(LocalDateTime.now());
            uConRepository.save(optionalUserConnection.get());
            for (UserRole one : optionalUserConnection.get().getConnectionUser().getUserRoles()) {
                oDto.getLevels().add(one.getRoleCodeLevel());
            }
        }
        return oDto;
    }

    public void dealWithSignout(UserDetailsiDTO iDto) throws AppException {

        Validators.validate(iDto);

        Optional<UserConnection> optionalUserConnection = uConRepository.findByConnectionId(iDto.getUserName());
        if (optionalUserConnection.isPresent()) {
            optionalUserConnection.get().setConnected(false);
            optionalUserConnection.get().setLastTimeConnected(LocalDateTime.now());
            uConRepository.save(optionalUserConnection.get());
        }

    }

    @Transactional
    public UserRegistryoDTO dealWithRegister(UserRegisteriDTO iDto) throws AppException {
        UserRegistryoDTO oDto = new UserRegistryoDTO();

        UserConnection userConnection = null;
        UserUser userUser = null;
        UserAddress userAddress = null;
        UserCredentials userCredentials = null;

        Validators.validate(iDto);

        Optional<UserConnection> optionalUserConnection = uConRepository.findByConnectionId(iDto.getConnectionId());
        if (optionalUserConnection.isPresent()) {
            throw new ItemAlreadyExistsException(iDto.getConnectionId());
        } else {
            userUser = new UserUser();
            userUser.setFirstName(iDto.getFirstName());
            userUser.setLastName(iDto.getLastName());
            userUser.addRole(rolRepository.findByRoleCode(iDto.getRequestedRole()));

            userAddress = new UserAddress(iDto.getEmail(), userUser);
            addRepository.save(userAddress);

            userUser.addAddress(userAddress);
            usrRepository.save(userUser);
            userCredentials = new UserCredentials(userUser, passwordEncoder.encode(iDto.getPassword()));
            crdRepository.save(userCredentials);

            userConnection = new UserConnection();
            userConnection.setConnectionId(iDto.getConnectionId());

            userConnection.setConnectionUser(userUser);
            userConnection.setConnectionUserCredentials(userCredentials);
            userConnection.setConnectionStatus(statusHelper.setItemStatus(Status.ACTIVE));
            uConRepository.save(userConnection);
            oDto.setConnection(userConnection);
        }
        return oDto;
    }

    public UserRolesoDTO  provideRoles() throws AppException {
        UserRolesoDTO oDto = new UserRolesoDTO();
            oDto.getRoleList().addAll(rolRepository.findAll());
            if (oDto.getRoleList().size() == 0){
                throw new UnexpectedEmptyResultException("Roles should exist. Check with the application Owner to Investigate");
            }
        return oDto;
    }

    public List<String> getLevels(String username, String password) {
       List<String> levels = new ArrayList<>();
        Optional<UserConnection> optionalUserConnection = uConRepository.findByConnectionId(username);
        if (optionalUserConnection.isPresent()) {
            optionalUserConnection.get().setConnected(true);
            optionalUserConnection.get().setLastTimeConnected(LocalDateTime.now());
            uConRepository.save(optionalUserConnection.get());
            for (UserRole one : optionalUserConnection.get().getConnectionUser().getUserRoles()) {
                levels.add(one.getRoleCodeLevel());
            }
        }
        return levels;
    }
}
