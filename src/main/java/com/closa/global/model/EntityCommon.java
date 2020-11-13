package com.closa.global.model;

import java.io.Serializable;
import  com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface EntityCommon extends Serializable {

    static final long serialVersionUID = 3844353125904473980L;

    default String toJson() {
        ObjectMapper om  = new ObjectMapper();
        String theJson = null ;
        try {
            theJson = om.writeValueAsString(this);
        }
        catch (JsonProcessingException e ) {
            theJson = this.toString();
        }
        return theJson;
    }
}
