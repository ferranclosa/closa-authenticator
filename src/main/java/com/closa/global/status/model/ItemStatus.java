package com.closa.global.status.model;

import com.closa.global.functions.InstantToString;
import com.closa.global.functions.StringToInstant;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.time.Instant;
import java.util.Objects;

@Embeddable
public class ItemStatus {
    @Column(name = "sts_cd", length = 1)
    private String statusCode;
    @Column(name = "sts_scd", length = 3)
    private String statusSubCode;
    @JsonSerialize(converter = InstantToString.class)
    @JsonDeserialize(converter = StringToInstant.class)
    @Column(name = "sts_ts")
    private Instant statusSetWhen;

    @Transient
    private String fullStatus;

    @Transient
    private Boolean isActive;


    public ItemStatus() {
    }

    public void setStatusSetWhen(Instant statusSetWhen) {
        this.statusSetWhen = statusSetWhen;
    }

    public void setFullStatus(String fullStatus) {
        this.fullStatus = fullStatus;
    }

    public Boolean getActive() {
        if (this.getStatusCode().equalsIgnoreCase("A"))
            return true;
        else
            return false;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusSubCode() {
        return statusSubCode;
    }

    public void setStatusSubCode(String statusSubCode) {
        this.statusSubCode = statusSubCode;
    }

    public Instant getStatusSetWhen() {
        return statusSetWhen;
    }

    public void setStatusSetWhen() {
        this.statusSetWhen = Instant.now();
    }

    public String getFullStatus() {
        return this.statusCode + "." + this.statusSubCode;
    }

    @Override
    public String toString() {
        return "ItemStatus{" +
                "statusCode='" + statusCode + '\'' +
                ", statusSubCode='" + statusSubCode + '\'' +
                ", statusSetWhen=" + statusSetWhen +
                ", fullStatus='" + fullStatus + '\'' +
                '}';
    }

    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemStatus that = (ItemStatus) o;
        return Objects.equals(statusCode, that.statusCode) &&
                Objects.equals(statusSubCode, that.statusSubCode) &&
                Objects.equals(statusSetWhen, that.statusSetWhen) &&
                Objects.equals(fullStatus, that.fullStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, statusSubCode, statusSetWhen, fullStatus);
    }
}
