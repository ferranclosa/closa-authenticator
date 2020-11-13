package com.closa.global.trace.dto;

import java.time.LocalTime;

public class EventBO {
    private Long eventId;
    private String whoEvent;
    private LocalTime whenEvent;
    private String apiCode;
    private char[] contents;

    public EventBO(Long eventId, String whoEvent, LocalTime whenEvent, String apiCode, char[] contents) {
        this.eventId = eventId;
        this.whoEvent = whoEvent;
        this.whenEvent = whenEvent;
        this.apiCode = apiCode;
        this.contents = contents;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getWhoEvent() {
        return whoEvent;
    }

    public void setWhoEvent(String whoEvent) {
        this.whoEvent = whoEvent;
    }

    public LocalTime getWhenEvent() {
        return whenEvent;
    }

    public void setWhenEvent(LocalTime whenEvent) {
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
}
