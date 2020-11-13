package com.closa.global.dto;

import com.closa.global.model.EntityCommon;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobaloDTO implements EntityCommon {
    private String returnCode;
    private String returnLabel;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> returnMessages = new ArrayList<>();
    private Integer lastPageProvided;
    private Integer lastItemsProvided;
    private Long totalNumberOfItems;
    private Integer totalNumberOfPages;

    public GlobaloDTO() {
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnLabel() {
        return returnLabel;
    }

    public void setReturnLabel(String returnLabel) {
        this.returnLabel = returnLabel;
    }

    public Integer getLastPageProvided() {
        return lastPageProvided;
    }

    public void setLastPageProvided(Integer lastPageProvided) {
        this.lastPageProvided = lastPageProvided;
    }

    public Integer getLastItemsProvided() {
        return lastItemsProvided;
    }

    public void setLastItemsProvided(Integer lastItemsProvided) {
        this.lastItemsProvided = lastItemsProvided;
    }

    public Long getTotalNumberOfItems() {
        return totalNumberOfItems;
    }

    public void setTotalNumberOfItems(Long totalNumberOfItems) {
        this.totalNumberOfItems = totalNumberOfItems;
    }

    public Integer getTotalNumberOfPages() {
        return totalNumberOfPages;
    }

    public void setTotalNumberOfPages(Integer totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
    }

    public List<String> getReturnMessages() {
        return returnMessages;
    }

    public void setReturnMessages(List<String> returnMessages) {
        this.returnMessages = returnMessages;
    }
    public void addMessage(String newMessage) {
        this.getReturnMessages().add(newMessage);
    }
}
