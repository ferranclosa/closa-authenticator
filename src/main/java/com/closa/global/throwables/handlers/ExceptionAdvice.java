package com.closa.global.throwables.handlers;

import com.closa.global.throwables.AppException;
import com.closa.global.throwables.MessageCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import java.time.LocalDateTime;

@RestControllerAdvice(basePackages = {"com.closa.authentication.controller"})
public class ExceptionAdvice {
    @ExceptionHandler(value = ServletException.class)
    public ResponseEntity<AppException> handleServletException(ServletException e) {
        AppException error = new AppException(MessageCode.AUT0005, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
            }
}
