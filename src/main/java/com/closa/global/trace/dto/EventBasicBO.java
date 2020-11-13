package com.closa.global.trace.dto;

import java.time.LocalDateTime;

public class EventBasicBO {
    private Long eventId;
    private LocalDateTime whenEvent;
    private String apiCode;

    public EventBasicBO(Long eventId, LocalDateTime whenEvent, String apiCode) {
        this.eventId = eventId;
        this.whenEvent = whenEvent;
        this.apiCode = apiCode;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
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

}
