package com.closa.global.trace.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Connection  {

    @Id
    private Long  id ;
    private String logonId;
    public Connection() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogonId() {
        return logonId;
    }

    public void setLogonId(String logonId) {
        this.logonId = logonId;
    }
}
