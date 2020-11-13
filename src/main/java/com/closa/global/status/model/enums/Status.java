package com.closa.global.status.model.enums;

import com.closa.global.model.EntityCommon;

public enum Status implements EntityCommon {
    ACTIVE("A.ACT"),
    DORMANT("A.DOR"),
    REJECTED("K.REJ"),
    DESACTIVATED("K.DES"),
    EXPIRED("K.EXP"),
    CLOSED("S.CLO"),
    DELETED("S.DEL"),
    BLOCKED("K.BLK"),
    PROCESSING("T.PRC"),
    WAITING_USERVALID("T.WUV"),
    WAITING_SATAFFVALID("T.WSV")

    ;
    private String value ;

    Status(String value ) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Status{" +
                "value='" + value + '\'' +
                '}';
    }

}
