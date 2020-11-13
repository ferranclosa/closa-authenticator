package com.closa.global.trace.model;

import com.closa.global.model.EntityCommon;
import com.closa.global.trace.model.enums.EventsHandled;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "evt_basic")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class EventBasic implements EntityCommon {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "EVENT_ID")
    @TableGenerator(name = "EVENT_ID",
            table ="GEN_ID",
            pkColumnName = "key_name",
            valueColumnName = "key_val",
            pkColumnValue = "EVENT_ID",
            initialValue = 0,
            allocationSize = 100 )
    private Long id;

    @ManyToOne( optional = true)
    @JoinColumn(name = "conn_id")
    private Connection connection;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name ="evt_hnd")
    private EventsHandled eventHandled;

    @Column(name="evt_when", nullable = false)
    private Instant whenEventHappened;

    @Column(name = "logon_id", length = 50, nullable = true)
    private String LogonId;

    public EventBasic() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogonId() {
        return LogonId;
    }

    public void setLogonId(String logonId) {
        LogonId = logonId;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public EventsHandled getEventHandled() {
        return eventHandled;
    }

    public void setEventHandled(EventsHandled eventHandled) {
        this.eventHandled = eventHandled;
    }

    public Instant getWhenEventHappened() {
        return whenEventHappened;
    }

    public void setWhenEventHappened() {
        this.whenEventHappened = Instant.now();
    }
}
