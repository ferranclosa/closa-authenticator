package com.closa.global.status.model;

import com.closa.global.model.EntityCommon;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "parm_sts_code")
public class StatusCodes implements EntityCommon {

    @Id
    @Column(name="status_code", length = 1, nullable = false, unique = true, insertable = false, updatable = false)
    private String statusCode;

    @Column(name="status_description", length = 255, nullable = false)
    private String statusDescription;

    @ManyToMany
    @JoinTable(
            name = "parm_status_cases",
            joinColumns = {@JoinColumn(name="fk_status")},
            inverseJoinColumns = {@JoinColumn(name="fk_author")})
    private List<StatusUseCase> useCases = new ArrayList<StatusUseCase>();

    @Transient
    private String useCaseString ;

    @Embedded
    private ItemStatus status;


    public StatusCodes()  {
    }

    public List<StatusUseCase> getUseCases() {
        return useCases;
    }

    public String getUseCaseString() {

        return useCaseString;
    }

    public void setUseCaseString() {
        List<StatusUseCase> cases = this.useCases;
        for (StatusUseCase one : cases){
            useCaseString = useCaseString + one.getUscCode().toString();
        }
    }

    public void setUseCases(List<StatusUseCase> useCases) {
        this.useCases = useCases;
    }

    public StatusCodes(String sc, String desc) {
        this.statusCode = sc;
        this.statusDescription = desc;

    }

    @Override
    public String toString() {
        return "StatusCodes{" +
                "statusCode='" + statusCode + '\'' +
                ", statusDescription='" + statusDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusCodes that = (StatusCodes) o;
        return Objects.equals(statusCode, that.statusCode) &&
                Objects.equals(statusDescription, that.statusDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, statusDescription);
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public void addUseCase(StatusUseCase usc){
        this.useCases.add(usc);
    }
    public void removeUseCase(StatusUseCase usc){
        this.useCases.remove(usc);
    }
}
