package com.closa.authentication.dto;

import com.closa.authentication.model.UserRole;
import com.closa.global.dto.GlobaloDTO;
import com.closa.global.model.EntityCommon;
import com.closa.global.security.model.User;
import com.closa.global.status.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.internal.util.type.PrimitiveWrapperHelper;
import org.hibernate.query.criteria.internal.predicate.BooleanExpressionPredicate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsoDTO extends GlobaloDTO {
    @JsonIgnore
    private User user;


    private String userName;
    private String password;
    private List<String> roles = new ArrayList<>();
    private List<String> authorities = new ArrayList<>();
    private Boolean isEnabled;
    private Boolean isBlocked;
    private Boolean isExpired;
    private Boolean isPasswordExpired;


    public UserDetailsoDTO() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setUserName() {
        this.userName = this.user.getUserName();
    }


    public void setPassword() {
        this.password = this.user.getPassword();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public Boolean getPasswordExpired() {
        return isPasswordExpired;
    }

    public void setRoles() {
        for (UserRole one : user.getUsrRoles()) {
            this.roles.add(one.getRoleCode());
        }

    }


    public void setAuthorities() {
        for (UserRole one : user.getUsrRoles()) {
            this.authorities.add(one.getRoleCodeLevel());
        }

    }


    public void setEnabled() {
        if (this.user.getUserStatus().getActive())
            isEnabled = true;
        else
            isEnabled = false;
    }


    public void setBlocked() {
        if (this.user.getUserStatus().getFullStatus().equalsIgnoreCase(Status.BLOCKED.toString()))
            isBlocked = true;
        else
            isBlocked = false;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired() {
        if (this.user.getUserStatus().getFullStatus().equalsIgnoreCase(Status.EXPIRED.toString()))
            isExpired = true;
        else
            isExpired = false;
    }


    public void setPasswordExpired() {
        if (this.user.getPasswordExpiresOn().isBefore(LocalDateTime.now()))
            isPasswordExpired = true;
        else
            isPasswordExpired = false;

    }
}
