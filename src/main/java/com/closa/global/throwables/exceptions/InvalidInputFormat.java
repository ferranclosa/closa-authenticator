package com.closa.global.throwables.exceptions;

import com.closa.global.throwables.AppException;
import com.closa.global.throwables.MessageCode;

public class InvalidInputFormat extends AppException {

    public InvalidInputFormat(String theWhat) {
        super(MessageCode.APP0003, theWhat);
    }
}
