package com.closa.global.trace.model;

import com.closa.global.functions.InstantToString;
import com.closa.global.model.EntityCommon;
import com.closa.global.trace.model.enums.EventsHandled;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "evt_stats")
public class EventStats implements EntityCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "EVT_STATS")
    @TableGenerator(name = "EVT_STATS",
            table = "GEN_ID",
            pkColumnName = "key_name",
            valueColumnName = "key_val",
            pkColumnValue = "EVT_STATS",
            initialValue = 0,
            allocationSize = 50)
    private Long id;

    @ManyToOne(optional = true)
    private Connection connection;

    @Column(name = "logon_id", length = 50, nullable = true)
    private String LogonId;

    @Column(name="evt_hnd", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventsHandled eventsHandled;

    @Column(name="chort_count")
    private Long shortTermCnt;

    @Column(name="long_count")
    private Long longTermCnt;

    @JsonSerialize(converter = InstantToString.class)
    @Column(name="first_record")
    private Instant firstEverRecorded;

    @JsonSerialize(converter = InstantToString.class)
    @Column(name="last_record")
    private Instant lastEverRecorded;

    public EventStats() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public EventsHandled getEventsHandled() {
        return eventsHandled;
    }

    public void setEventsHandled(EventsHandled eventsHandled) {
        this.eventsHandled = eventsHandled;
    }

    public Long getShortTermCnt() {
        return shortTermCnt;
    }

    public void setShortTermCnt(Long shortTermCnt) {
        this.shortTermCnt = shortTermCnt;
    }

    public Long getLongTermCnt() {
        return longTermCnt;
    }

    public void setLongTermCnt(Long longTermCnt) {
        this.longTermCnt = longTermCnt;
    }

    public Instant getFirstEverRecorded() {
        return firstEverRecorded;
    }

    public void setFirstEverRecorded(Instant firstEverRecorded) {
        this.firstEverRecorded = firstEverRecorded;
    }

    public Instant getLastEverRecorded() {
        return lastEverRecorded;
    }

    public void setLastEverRecorded(Instant lastEverRecorded) {
        this.lastEverRecorded = lastEverRecorded;
    }

    public String getLogonId() {
        return LogonId;
    }

    public void setLogonId(String logonId) {
        LogonId = logonId;
    }
}
