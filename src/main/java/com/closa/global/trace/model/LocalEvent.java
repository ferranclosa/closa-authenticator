package com.closa.global.trace.model;

import com.closa.global.functions.LocalDateTimeToString;
import com.closa.global.model.EntityCommon;
import com.closa.global.trace.model.enums.AuditFileStatus;
import com.closa.global.trace.model.enums.EventsHandled;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="parm_evt_local")
public class LocalEvent implements  EntityCommon {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "EVT_ID")
    @TableGenerator(name = "EVT_ID",
    table ="GEN_ID",
    pkColumnName = "key_name",
    valueColumnName = "key_val",
    pkColumnValue = "EVT_ID",
    initialValue = 0,
    allocationSize = 100 )
    private Long id;

    @Column(name = "who_evt", length = 45)
    private String whoEvent;

    @Column(name = "when_evt")
    @JsonSerialize(converter = LocalDateTimeToString.class)
    private LocalDateTime whenEvent;

    @Column(name = "api_cod", length = 45)
    private String apiCode;

    @Column(name = "contents")
    private char[] contents;

    @Enumerated(EnumType.STRING)
    @Column(name= "rec_sts", length= 10)
    private AuditFileStatus status;

    public LocalEvent(String whoEvent,  String apiCode, String  contents) {
        this.whoEvent = whoEvent;
        this.whenEvent = LocalDateTime.now();
        this.apiCode = apiCode;
        this.contents = contents.toCharArray();
        this.status = AuditFileStatus.LOCAL;
    }

    public LocalEvent() {

    }


    public LocalEvent(String logonId, EventsHandled eventsHandled, String toJson) {
     this.whoEvent = logonId;
     this.whenEvent = LocalDateTime.now();
     this.contents = toJson.toCharArray();
     this.apiCode = eventsHandled.toString();
     this.status = AuditFileStatus.LOCAL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWhoEvent() {
        return whoEvent;
    }

    public void setWhoEvent(String whoEvent) {
        this.whoEvent = whoEvent;
    }

    public LocalDateTime getWhenEvent() {
        return whenEvent;
    }

    public void setWhenEvent(LocalDateTime whenEvent) {
        this.whenEvent = whenEvent;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public char[] getContents() {
        return contents;
    }

    public void setContents(char[] contents) {
        this.contents = contents;
    }

    public AuditFileStatus getStatus() {
        return status;
    }

    public void setStatus(AuditFileStatus status) {
        this.status = status;
    }
}
