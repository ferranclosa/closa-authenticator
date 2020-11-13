package com.closa.global.throwables.messages;

import com.closa.global.throwables.AppMessage;
import com.closa.global.throwables.MessageCode;

public class ResultsCompletionMessage extends AppMessage {
    public ResultsCompletionMessage(String theWhat) {
        super(MessageCode.APP0009, theWhat);
    }
}
