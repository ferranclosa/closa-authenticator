package com.closa.global.trace.model.enums;

public enum EventsHandled {
    ACTIVATED("RECORD HAS BEEN REACTIVATED"),
    CREATED("RECORD HAS BEEN ADDED"),
    UPDATED("RECORD HAS BEEN UPDATED"),
    DELETED("RECORD HAS BEEN DELETED OR FLAGGED AS DELETED"),
    PURGED("RECORD HAS BEEN PURGED"),
    SUSPENDED("SUSPENDED RECORD"),
    APPROVED("RECORD HAS BEEN APPROVED"),
    REJECTED("RECORD HAS BEEN REJECTED"),
    LOGIN("USER HAS LOGGED IN"),
    LOGIN_FAILED("USER HAS ENTERED INCORRECT CREDENTIALS"),
    LOGOUT("USER HAS LOGGED OUT"),
    LOGOUT_FAILED("SOMETHING WENT WRONG WHEN LOGOUT"),
    REGISTER("USER HAS NEWLY REGISTERED"),
    REGISTER_FAILED("SOMETHING WENT WRONG DURING REGISTRATION"),
    CREATED_FAILED("SOMETHING WENT WRONG WITH RECORD CREATION"),
    USER_DETAILS_PROVIDED("USER DETAILS PROVIDED"),
    USER_DETAILS_DENIED("USER DETAILS DENIED"),
    ;

    private String name;

    EventsHandled() {
    }

    EventsHandled(String s) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EventsHandled{" +
                "name='" + name + '\'' +
                '}';
    }
    public static EventsHandled fromName(String pname){
        if (pname == null ){
            return null;
        }
        for (EventsHandled one: EventsHandled.values()){
            if(one.name.equalsIgnoreCase(pname)){
                return one;
            }
        }
        throw new IllegalArgumentException(("ShortName [" + pname + "] not Supported"));
    }
}
