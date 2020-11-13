package com.closa.global.throwables.exceptions;

import com.closa.global.throwables.AppException;
import com.closa.global.throwables.MessageCode;

public class ItemAlreadyExistsException extends AppException {
    public ItemAlreadyExistsException( String theWhat) {
        super(MessageCode.APP0002, theWhat);
    }
}
