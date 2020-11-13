package com.closa.global.status.model;

import com.closa.global.model.EntityCommon;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="parm_sts_sub_code")
public class StatusSubCode implements EntityCommon {
    @Id
    @Column(name= "substatus_code", length = 3, nullable = false, updatable = false, insertable = false, unique = true)
    private String stsSubCode;
    @Column(name= "substatus_description", length = 255, nullable = false)
    private String stsScdDescription;
    @ManyToOne(optional = true)
    @JoinColumn(name= "scd_belongs_to", foreignKey = @ForeignKey(name = "FK_BelongsTo" ), nullable = true)
    private StatusCodes belongsTo;
    @Embedded
    private ItemStatus status;

    public StatusSubCode() {
    }

    public StatusSubCode(String code, String desc, StatusCodes belongsTo) {
        this.stsSubCode = code;
        this.stsScdDescription= desc;
        this.belongsTo= belongsTo;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusSubCode that = (StatusSubCode) o;
        return Objects.equals(stsSubCode, that.stsSubCode) &&
                Objects.equals(stsScdDescription, that.stsScdDescription) &&
                Objects.equals(belongsTo, that.belongsTo) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stsSubCode, stsScdDescription, belongsTo, status);
    }

    @Override
    public String toString() {
        return "StatusSubCode{" +
                "stsSubCode='" + stsSubCode + '\'' +
                ", stsScdDescription='" + stsScdDescription + '\'' +
                ", belongsTo=" + belongsTo +
                ", status=" + status +
                '}';
    }

    public String getStsSubCode() {
        return stsSubCode;
    }

    public void setStsSubCode(String stsSubCode) {
        this.stsSubCode = stsSubCode;
    }

    public String getStsScdDescription() {
        return stsScdDescription;
    }

    public void setStsScdDescription(String stsScdDescription) {
        this.stsScdDescription = stsScdDescription;
    }

    public StatusCodes getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(StatusCodes belongsTo) {
        this.belongsTo = belongsTo;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }
}
