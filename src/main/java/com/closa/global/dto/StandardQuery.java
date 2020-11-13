package com.closa.global.dto;

public class StandardQuery  extends GlobaliDTO {
    private String searchBy;
    private String SearchByWhat;

    public StandardQuery() {
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getSearchByWhat() {
        return SearchByWhat;
    }

    public void setSearchByWhat(String searchByWhat) {
        SearchByWhat = searchByWhat;
    }
}
