package com.closa.authentication.controller;

import com.closa.authentication.dto.JwtRequest;
import com.closa.authentication.dto.JwtResponse;
import com.closa.authentication.dto.UserRegisteriDTO;
import com.closa.authentication.functions.JwtTokenHelper;
import com.closa.authentication.services.AuthService;
import com.closa.global.security.service.UserService;
import com.closa.global.throwables.MessageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    AuthService authService;

    @Autowired
    private JwtTokenHelper jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest loginUser) throws AuthenticationException {
        JwtResponse oDto = new JwtResponse();
        /**
         * Get the levels of authority from user model to tat they can be added to the jwt
         */
        List<String> levels = authService.getLevels(loginUser.getUsername(), loginUser.getPassword());

        /**
         * Get the authentication object to manage roles and add them to jwt
         */
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication, levels);
        oDto.setJwttoken(token);
        oDto.setReturnCode(MessageCode.APP0000.getrCode());
        oDto.setReturnLabel(MessageCode.APP0000.getmMsg());
        oDto.setLevels(levels);
       // jwtTokenUtil.getLevelClaimsFromToken(token);
        return ResponseEntity.ok(oDto);
    }





    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/adminping", method = RequestMethod.GET)
    public String adminPing(){
        return "Only Admins Can Read This";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/userping", method = RequestMethod.GET)
    public String userPing(){
        return "Any User Can Read This";
    }
}
