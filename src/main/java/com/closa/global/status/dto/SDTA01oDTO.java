package com.closa.global.status.dto;

import com.closa.global.dto.StandardQueryResponse;
import com.closa.global.status.model.bo.BoStatus;
import com.closa.global.status.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class SDTA01oDTO extends StandardQueryResponse {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)

    List<Status> listOfStatus = new ArrayList<>();
    @JsonInclude(JsonInclude.Include.NON_EMPTY)

    List<String> listOfStatusString = new ArrayList<>();
    @JsonInclude(JsonInclude.Include.NON_EMPTY)

    List<BoStatus> listOfDynamicStatus = new ArrayList<>();

    public SDTA01oDTO() {
    }

    public List<Status> getListOfStatus() {
        return listOfStatus;
    }

    public void setListOfStatus(List<Status> listOfStatus) {
        this.listOfStatus = listOfStatus;
    }

    public List<String> getListOfStatusString() {
        return listOfStatusString;
    }

    public void setListOfStatusString(List<String> listOfStatusString) {
        this.listOfStatusString = listOfStatusString;
    }

    public List<BoStatus> getListOfDynamicStatus() {
        return listOfDynamicStatus;
    }

    public void setListOfDynamicStatus(List<BoStatus> listOfDynamicStatus) {
        this.listOfDynamicStatus = listOfDynamicStatus;
    }
}
