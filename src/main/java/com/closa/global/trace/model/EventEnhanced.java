package com.closa.global.trace.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="evt_enhanced")
public class EventEnhanced extends EventBasic {



    @Lob
    private char[] bigData;

    public EventEnhanced(char[] bigData) {
        this.bigData = bigData;
    }

    public EventEnhanced() {
    }

    public char[] getBigData() {
        return bigData;
    }

    public void setBigData(char[] bigData) {
        this.bigData = bigData;
    }
}
