package com.closa.global.security.model;

import com.closa.authentication.model.UserConnection;
import com.closa.authentication.model.UserRole;
import com.closa.global.model.EntityCommon;
import com.closa.global.status.model.ItemStatus;
import com.closa.global.status.model.enums.Status;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Embedded;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class User  implements EntityCommon {
    private String userName;
    private String password;
    private LocalDateTime passwordExpiresOn;
    @Embedded
    private ItemStatus userStatus;

    private List<String> roles = new ArrayList<>();
    private List<String> authorities = new ArrayList<>();
    private Boolean isEnabled;
    private Boolean isBlocked;
    private Boolean isExpired;
    private Boolean isPasswordExpired;

    private List<UserRole> usrRoles = new ArrayList<>();


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ItemStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(ItemStatus userStatus) {
        this.userStatus = userStatus;
    }


    public User(UserConnection connection) {
        this.userName = connection.getConnectionId();
        this.password = connection.getConnectionUserCredentials().getCredentialsPassword();
        this.userStatus = connection.getConnectionStatus();
        this.usrRoles = connection.getConnectionUser().getUserRoles();

        this.passwordExpiresOn = connection.getConnectionUserCredentials().getExpiresWhen();
        this.setBlocked();
        this.setEnabled();
        this.setPasswordExpired();
        this.setRoles();
        this.setAuthorities();
    }

    public LocalDateTime getPasswordExpiresOn() {
        return passwordExpiresOn;
    }

    public void setPasswordExpiresOn(LocalDateTime passwordExpiresOn) {
        this.passwordExpiresOn = passwordExpiresOn;
    }

    public List<UserRole> getUsrRoles() {
        return usrRoles;
    }

    public void setUsrRoles(List<UserRole> usrRoles) {
        this.usrRoles = usrRoles;
    }

    public List<String> getRoles() {
        return this.roles;
    }


    public List<String> getAuthorities() {
        return this.authorities;
    }


    public Boolean getEnabled() {
        return this.isEnabled;
    }


    public void setBlocked() {
        if (this.getUserStatus().getFullStatus().equalsIgnoreCase(Status.BLOCKED.toString()))
            this.isBlocked = true;
        else
            this.isBlocked =  false;
    }


    public Boolean getExpired() {
        return this.isExpired;
    }

    public void setRoles() {
        for (UserRole one : this.getUsrRoles()) {
            this.roles.add(one.getRoleCode());
        }

    }


    public void setAuthorities() {
        for (UserRole one : this.getUsrRoles()) {
            this.authorities.add(one.getRoleCodeLevel());
        }
    }


    public void setEnabled() {
        if (this.getUserStatus().getActive())
            this.isEnabled = true;
        else
            this.isEnabled = false;
    }


    public Boolean getBlocked() {
        return  this.isBlocked;
    }




    public Boolean getPasswordExpired() {
        return this.isPasswordExpired;
}


    public void setPasswordExpired() {
        if (this.getPasswordExpiresOn().isBefore(LocalDateTime.now()))
            this.isPasswordExpired = true;
        else
            this.isPasswordExpired =  false;
    }

}
