package com.closa.authentication.model;

import com.closa.global.status.model.ItemStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CONN_ID")
    @TableGenerator(name = "CONN_ID",
            table ="GEN_ID",
            pkColumnName = "key_name",
            valueColumnName = "key_val",
            pkColumnValue = "CONN_ID",
            initialValue = 0,
            allocationSize = 100 )
    private Long id;

    private String connectionId;
    @Embedded
    private ItemStatus connectionStatus;

    private Boolean isConnected;
    private LocalDateTime lastTimeConnected;

    @ManyToOne
    private UserUser connectionUser;

    @OneToOne
    private UserCredentials connectionUserCredentials;

    public UserConnection() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public ItemStatus getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(ItemStatus connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public UserUser getConnectionUser() {
        return connectionUser;
    }

    public void setConnectionUser(UserUser connectionUser) {
        this.connectionUser = connectionUser;
    }

    public UserCredentials getConnectionUserCredentials() {
        return connectionUserCredentials;
    }

    public void setConnectionUserCredentials(UserCredentials connectionUserCredentials) {
        this.connectionUserCredentials = connectionUserCredentials;

    }

    public Boolean getConnected() {
        return isConnected;
    }

    public void setConnected(Boolean connected) {
        isConnected = connected;
    }

    public LocalDateTime getLastTimeConnected() {
        return lastTimeConnected;
    }

    public void setLastTimeConnected(LocalDateTime lastTimeConnected) {
        this.lastTimeConnected = lastTimeConnected;
    }
}
