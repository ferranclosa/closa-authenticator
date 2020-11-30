package com.closa.authentication.controller;

import com.closa.authentication.dto.*;
import com.closa.authentication.services.AuthService;
import com.closa.global.dto.GlobaloDTO;
import com.closa.global.functions.JwtUtils;
import com.closa.global.throwables.AppException;
import com.closa.global.throwables.MessageCode;
import com.closa.global.throwables.exceptions.UserNotFoundException;
import com.closa.global.trace.model.enums.EventsHandled;
import com.closa.global.trace.service.EventService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtils jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EventService eventService;

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserDetailsoDTO getUserDetails(@RequestBody UserDetailsiDTO iDto) throws Exception {
        UserDetailsoDTO oDto = new UserDetailsoDTO();

        try {
            oDto = authService.dealProvideUserDetails(iDto);
            eventService.insertEvent(iDto.getUserName(),EventsHandled.USER_DETAILS_PROVIDED, oDto.toJson());
        } catch (AppException e){
            oDto.setReturnCode(e.getMessageCode().getrCode());
            oDto.setReturnLabel(e.getMessageText());
            oDto.setReturnMessages(e.getMultiExceptions());
            eventService.insertEvent(iDto.getUserName(),EventsHandled.USER_DETAILS_DENIED);
        }
        catch (Exception e) {
            oDto.setReturnCode(MessageCode.APP0099.getrCode());
            oDto.setReturnLabel(MessageCode.APP0099.getmMsg());
            oDto.setReturnMessages(Arrays.asList(ExceptionUtils.getRootCauseStackTrace(e)));
            eventService.insertEvent(iDto.getUserName(),EventsHandled.USER_DETAILS_DENIED);
        }
        return oDto;
    }

   // @CrossOrigin
    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponse generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {
        JwtResponse oDto = new JwtResponse();
        try {
            oDto = authService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());
            if(userDetails == null){
                throw new UserNotFoundException(authenticationRequest.getUsername());
            }
            final String token = jwtUtil.generateToken(userDetails);
            oDto.setReturnCode(MessageCode.APP0000.getrCode());
            oDto.setReturnLabel(MessageCode.APP0000.getmMsg());
            oDto.setJwttoken(token);
            eventService.insertEvent(authenticationRequest.getUsername(), EventsHandled.LOGIN);
        } catch (AppException e ){
            oDto.setReturnCode(e.getMessageCode().getrCode());
            oDto.setReturnLabel(e.getMessageText());
            eventService.insertEvent(authenticationRequest.getUsername(), EventsHandled.LOGIN_FAILED, oDto.toJson());
        } catch (Exception e) {
            oDto.setReturnCode(MessageCode.APP0099.getrCode());
            oDto.setReturnLabel(MessageCode.APP0099.getmMsg());
            oDto.setReturnMessages(Arrays.asList(ExceptionUtils.getRootCauseStackTrace(e)));
            eventService.insertEvent(authenticationRequest.getUsername(), EventsHandled.LOGIN_FAILED, oDto.toJson());
        }
            return oDto;
    }

    //@CrossOrigin
    @PostMapping(value = "/signout", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GlobaloDTO dealWithSignout(@RequestBody UserDetailsiDTO iDto)
            throws Exception {
        GlobaloDTO oDto = new GlobaloDTO();
        try {
            authService.dealWithSignout(iDto);
            oDto.setReturnCode(MessageCode.APP0000.getrCode());
            oDto.setReturnLabel(MessageCode.APP0000.getmMsg());
            eventService.insertEvent(iDto.getUserName(), EventsHandled.LOGOUT);
        } catch (AppException e ){
            oDto.setReturnCode(e.getMessageCode().getrCode());
            oDto.setReturnLabel(e.getMessageText());
            eventService.insertEvent(iDto.getUserName(), EventsHandled.LOGOUT_FAILED);
        } catch (Exception e) {
            oDto.setReturnCode(MessageCode.APP0099.getrCode());
            oDto.setReturnLabel(MessageCode.APP0099.getmMsg());
            oDto.setReturnMessages(Arrays.asList(ExceptionUtils.getRootCauseStackTrace(e)));
            eventService.insertEvent(iDto.getUserName(), EventsHandled.LOGOUT_FAILED);
        }
        return oDto;
    }

    //@CrossOrigin
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRegistryoDTO registerNewUser(@RequestBody UserRegisteriDTO iDto)
            throws Exception {
        UserRegistryoDTO oDto = new UserRegistryoDTO();
        try {
            oDto = authService.dealWithRegister(iDto);
            oDto.setReturnCode(MessageCode.APP0000.getrCode());
            oDto.setReturnLabel(MessageCode.APP0000.getmMsg());
            eventService.insertEvent(iDto.getConnectionId(), EventsHandled.CREATED, oDto.toJson());
        } catch (AppException e ){
            oDto.setReturnCode(e.getMessageCode().getrCode());
            oDto.setReturnLabel(e.getMessageText());
            oDto.getReturnMessages().addAll(e.getMultiExceptions());
            eventService.insertEvent(iDto.getConnectionId(), EventsHandled.CREATED_FAILED, oDto.toJson());
        } catch (Exception e) {
            oDto.setReturnCode(MessageCode.APP0099.getrCode());
            oDto.setReturnLabel(MessageCode.APP0099.getmMsg());
            oDto.setReturnMessages(Arrays.asList(ExceptionUtils.getRootCauseStackTrace(e)));
            eventService.insertEvent(iDto.getConnectionId(), EventsHandled.CREATED_FAILED, oDto.toJson());
        }
        return oDto;
    }

   // @CrossOrigin
    @GetMapping(value = "/roles",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRolesoDTO getRoles() throws Exception {
        UserRolesoDTO oDto = new UserRolesoDTO();
        try {
            oDto = authService.provideRoles();
            oDto.setReturnCode(MessageCode.APP0000.getrCode());
            oDto.setReturnLabel(MessageCode.APP0000.getmMsg());
        } catch (AppException e ){
            oDto.setReturnCode(e.getMessageCode().getrCode());
            oDto.setReturnLabel(e.getMessageText());
        } catch (Exception e) {
            oDto.setReturnCode(MessageCode.APP0099.getrCode());
            oDto.setReturnLabel(MessageCode.APP0099.getmMsg());
            oDto.setReturnMessages(Arrays.asList(ExceptionUtils.getRootCauseStackTrace(e)));
        }
        return oDto;
    }
}


