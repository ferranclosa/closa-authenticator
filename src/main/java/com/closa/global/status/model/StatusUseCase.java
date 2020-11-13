package com.closa.global.status.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parm_sts_use_case")
public class StatusUseCase {
    @Id
    @Column(name = "usc_code", nullable = false, length = 1)
    private String uscCode;
    @Column(name = "usc_description", length = 75, nullable = true)
    private String uscDescription;

    public StatusUseCase() {
    }

    public String getUscCode() {
        return uscCode;
    }

    public void setUscCode(String uscCode) {
        this.uscCode = uscCode;
    }

    public String getUscDescription() {
        return uscDescription;
    }

    public void setUscDescription(String uscDescription) {
        this.uscDescription = uscDescription;
    }
}
