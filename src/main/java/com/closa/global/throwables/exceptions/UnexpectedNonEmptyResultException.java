package com.closa.global.throwables.exceptions;

import com.closa.global.throwables.AppException;
import com.closa.global.throwables.MessageCode;

public class UnexpectedNonEmptyResultException extends AppException {

    public UnexpectedNonEmptyResultException(String theWhat) {
        super(MessageCode.APP0007, theWhat);
    }
}
