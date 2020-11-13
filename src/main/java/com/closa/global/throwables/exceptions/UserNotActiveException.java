package com.closa.global.throwables.exceptions;

import com.closa.global.throwables.AppException;
import com.closa.global.throwables.MessageCode;

public class UserNotActiveException extends AppException {
    public UserNotActiveException(String theWhat) {
        super(MessageCode.AUT0002, theWhat);
    }
}
