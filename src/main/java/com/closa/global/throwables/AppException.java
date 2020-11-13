package com.closa.global.throwables;

import com.closa.global.dto.GlobaloDTO;
import com.closa.global.model.EntityCommon;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class AppException extends Exception implements EntityCommon {
    private MessageCode messageCode;
    private String messageText;
    private String rCode;
    @JsonIgnore
    private Exception e;
    private String theWhat;
    private HttpStatus httpStatus;

    public AppException(Exception e ){
        this.e = e;
    }

    public AppException(String message, MessageCode messageCode, String theWhat) {
        super(message);
        this.messageCode = messageCode;
        this.theWhat = theWhat;
        this.messageText = "{" + messageCode.getmCode() + "} " + messageCode.getmMsg() + " [" + theWhat + "]";
    }

    public AppException(MessageCode messageCode, String rCode, String theWhat) {
        this.messageCode = messageCode;
        this.rCode = rCode;
        this.theWhat = theWhat;
        this.messageText = "{" + messageCode.getmCode() + "} " + messageCode.getmMsg() + " [" + theWhat + "]";
    }

    public AppException(MessageCode messageCode, String theWhat) {
        this.messageCode = messageCode;
        this.theWhat = theWhat;
        this.messageText = "{" + messageCode.getmCode() + "} " + messageCode.getmMsg() + " [" + theWhat + "]";
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(MessageCode messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getrCode() {
        return rCode;
    }

    public void setrCode(String rCode) {
        this.rCode = rCode;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public String getTheWhat() {
        return theWhat;
    }

    public void setTheWhat(String theWhat) {
        this.theWhat = theWhat;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
