package com.closa.global.throwables.exceptions;

import com.closa.global.throwables.AppMessage;
import com.closa.global.throwables.MessageCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NotAuthorisedtoRESTException implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
        AppMessage msg = new AppMessage(MessageCode.AUT0005, "You are not Authorised");
        res.getWriter()
                .write(msg.toJson());
    }
}