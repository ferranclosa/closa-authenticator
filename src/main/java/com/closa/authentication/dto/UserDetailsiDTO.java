package com.closa.authentication.dto;

import com.closa.global.model.EntityCommon;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDetailsiDTO implements EntityCommon {

    @NotNull
    @NotEmpty
    @NotBlank
    @Length(min = 8, message = "This is a Test for minimum length")
    private String userName;

    public UserDetailsiDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
