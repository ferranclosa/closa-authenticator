package com.closa.global.throwables.exceptions;

import com.closa.global.throwables.AppException;
import com.closa.global.throwables.MessageCode;

public class UserNotFoundException extends AppException {
    public UserNotFoundException(String theWhat) {
        super(MessageCode.AUT0001, theWhat);
    }
}
