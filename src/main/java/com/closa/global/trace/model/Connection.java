package com.closa.global.trace.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public abstract class Connection  {

    @Id
    private Long  id ;
    private String logonId;
    private LocalDateTime startedWhen;
    private LocalDateTime finishedWhen;
    public Connection() {
    }

    public LocalDateTime getStartedWhen() {
        return startedWhen;
    }

    public void setStartedWhen(LocalDateTime startedWhen) {
        this.startedWhen = startedWhen;
    }

    public LocalDateTime getFinishedWhen() {
        return finishedWhen;
    }

    public void setFinishedWhen(LocalDateTime finishedWhen) {
        this.finishedWhen = finishedWhen;
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
