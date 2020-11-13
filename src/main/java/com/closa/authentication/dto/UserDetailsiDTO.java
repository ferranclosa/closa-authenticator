package com.closa.authentication.dto;

import com.closa.global.model.EntityCommon;

public class UserDetailsiDTO implements EntityCommon {

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
