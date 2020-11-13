package com.closa.global.throwables.exceptions;

import com.closa.global.throwables.AppException;
import com.closa.global.throwables.MessageCode;

public class InvalidCredentialsException extends AppException {
    public InvalidCredentialsException(String theWhat) {
        super(MessageCode.AUT0003, theWhat);
    }
}
