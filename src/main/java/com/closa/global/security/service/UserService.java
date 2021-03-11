package com.closa.global.security.service;

import com.closa.authentication.dao.UConRepository;
import com.closa.global.throwables.exceptions.UserNotActiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UConRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<com.closa.global.security.model.User> ouser = userRepository.provideUserDetails(userName.trim());

        User usr = null;
        if (ouser.isPresent()){
            String username  = ouser.get().getUserName();
            String password  = ouser.get().getPassword();
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (String one: ouser.get().getRoles()){
                authorities.add(new SimpleGrantedAuthority("ROLE_"+ one));
            }
            usr = new User(username, password, authorities);
        } else {
            throw new UsernameNotFoundException(userName);
        }
        return usr;
    }

}
