package com.closa.global.status.model.bo;

import com.closa.global.status.model.StatusCodes;
import com.closa.global.status.model.StatusUseCase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoStatus {

    @JsonIgnore
    private StatusCodes theFullObject;
    private String compositeStatus;
    private String description;
    private String cases;


    public BoStatus(StatusCodes theFullObject, String compositeStatus, String description) {
        this.theFullObject = theFullObject;
        this.compositeStatus = compositeStatus;
        this.description = description;
    }

    public BoStatus() {
    }

    public StatusCodes getTheFullObject() {
        return theFullObject;
    }

    public void setTheFullObject(StatusCodes theFullObject) {
        this.theFullObject = theFullObject;
    }

    public String getCases() {
        return cases;
    }



    public void setCases(List<StatusUseCase> useCases) {
        String result = "";
        List<StatusUseCase> list = useCases;
        for (StatusUseCase one : list){
            result  = result.trim() + one.getUscCode().toString();
        }
        this.cases = result;
    }

    public BoStatus(String compositeStatus, String description) {
        this.compositeStatus = compositeStatus;
        this.description = description;
    }


    public String getCompositeStatus() {
        return compositeStatus;
    }

    public void setCompositeStatus(String compositeStatus) {
        this.compositeStatus = compositeStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
