package com.closa.authentication.dto;

import com.closa.authentication.model.UserConnection;
import com.closa.global.dto.GlobaloDTO;

public class UserRegistryoDTO extends GlobaloDTO {
    UserConnection connection ;

    public UserRegistryoDTO() {
    }

    public UserConnection getConnection() {
        return connection;
    }

    public void setConnection(UserConnection connection) {
        this.connection = connection;
    }
}
