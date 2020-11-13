package com.closa.global.throwables.exceptions;

import com.closa.global.throwables.AppMessage;
import com.closa.global.throwables.MessageCode;
import lombok.SneakyThrows;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedExceptionHandler  implements AccessDeniedHandler  {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse res, org.springframework.security.access.AccessDeniedException e) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);

        throw new ServletException("UNAUTHORISED");

    }
}
