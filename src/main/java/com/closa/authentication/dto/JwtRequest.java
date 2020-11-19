package com.closa.authentication.dto;

import com.closa.global.model.EntityCommon;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class JwtRequest implements EntityCommon {

    @NotEmpty
    @NotBlank
    @NotNull
    private String username;
    @NotEmpty
    @NotBlank
    @NotNull

    private String password;

    //default constructor for JSON Parsing
    public JwtRequest()
    {
    }

    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}