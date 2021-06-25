package com.closa.authentication.dto;

import com.closa.global.dto.GlobaloDTO;
import com.closa.global.model.EntityCommon;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JwtResponse extends GlobaloDTO {

    private  String jwttoken;

    private List<String > levels = new ArrayList<>();

    public JwtResponse() {
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public void setJwttoken(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public List<String> getLevels() {
        return levels;
    }

    public void setLevels(List<String> levels) {
        this.levels = levels;
    }
}