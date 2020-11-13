package com.closa.global.throwables.messages;

import com.closa.global.throwables.AppMessage;
import com.closa.global.throwables.MessageCode;

public class CompletionMessage extends AppMessage {
    public CompletionMessage( String theWhat) {
        super(MessageCode.APP0000, theWhat);
    }
}
