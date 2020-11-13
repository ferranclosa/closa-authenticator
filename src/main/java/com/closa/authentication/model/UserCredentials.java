package com.closa.authentication.model;

import com.closa.global.model.EntityCommon;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserCredentials implements EntityCommon {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CRED_ID")
    @TableGenerator(name = "CRED_ID",
            table ="GEN_ID",
            pkColumnName = "key_name",
            valueColumnName = "key_val",
            pkColumnValue = "CRED_ID",
            initialValue = 0,
            allocationSize = 100 )
    private Long id;
    @ManyToOne
    private UserUser theUser;
    private String credentialsPassword;
    private LocalDateTime setWhen;
    //@Transient
    private LocalDateTime expiresWhen;

    public LocalDateTime getSetWhen() {
        return setWhen;
    }


    public void setSetWhen() {
        this.setWhen = LocalDateTime.now();
    }

    public UserCredentials() {
    }

    public UserUser getTheUser() {
        return theUser;
    }

    public void setTheUser(UserUser theUser) {
        this.theUser = theUser;
    }

    public UserCredentials(UserUser user,  String password){
        this.theUser = user;
        this.credentialsPassword = password;
        this.setSetWhen();
        this.setExpiresWhen();
    }
    public String getCredentialsPassword() {
        return credentialsPassword;
    }

    public LocalDateTime getExpiresWhen() {
        return expiresWhen;
    }

    public void setExpiresWhen() {

        this.expiresWhen = LocalDateTime.now().plusMonths(3);
    }

    public void setCredentialsPassword(String credentialsPassword) {
        this.credentialsPassword = credentialsPassword;
    }
}
