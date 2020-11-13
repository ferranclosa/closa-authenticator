package com.closa.global.dto;

public class StandardQueryResponse extends GlobaloDTO{
    private String searchedBy;
    private String searchedByWhat;

    public StandardQueryResponse() {
    }

    public String getSearchedBy() {
        return searchedBy;
    }

    public void setSearchedBy(String searchedBy) {
        this.searchedBy = searchedBy;
    }

    public String getSearchedByWhat() {
        return searchedByWhat;
    }

    public void setSearchedByWhat(String searchedByWhat) {
        this.searchedByWhat = searchedByWhat;
    }
}
