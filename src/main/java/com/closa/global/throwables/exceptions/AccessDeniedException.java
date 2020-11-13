package com.closa.global.throwables.exceptions;

import com.closa.global.throwables.AppException;
import com.closa.global.throwables.MessageCode;

public class AccessDeniedException extends AppException {
    public AccessDeniedException(String theWhat) {
        super(MessageCode.AUT0005, theWhat);
    }
}
