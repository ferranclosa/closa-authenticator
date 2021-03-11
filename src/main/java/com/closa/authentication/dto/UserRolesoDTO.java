package com.closa.authentication.dto;

import com.closa.authentication.model.UserRole;
import com.closa.global.dto.GlobaloDTO;

import java.util.ArrayList;
import java.util.List;

public class UserRolesoDTO extends GlobaloDTO {
    private List<UserRole> roleList = new ArrayList<>();

    public UserRolesoDTO() {
    }

    public List<UserRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UserRole> roleList) {
        this.roleList = roleList;
    }


}
