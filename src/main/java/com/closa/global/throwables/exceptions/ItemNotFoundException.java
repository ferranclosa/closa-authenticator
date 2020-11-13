package com.closa.global.throwables.exceptions;

import com.closa.global.throwables.AppException;
import com.closa.global.throwables.MessageCode;

public class ItemNotFoundException extends AppException {
    public ItemNotFoundException( String theWhat) {
        super(MessageCode.APP0001, theWhat);
    }
}
