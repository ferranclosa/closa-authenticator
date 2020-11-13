package com.closa.global.throwables.exceptions;

import com.closa.global.throwables.AppException;
import com.closa.global.throwables.MessageCode;

public class UnexpectedEmptyResultException extends AppException {

    public UnexpectedEmptyResultException(String theWhat) {
        super(MessageCode.APP0006, theWhat);
    }
}
