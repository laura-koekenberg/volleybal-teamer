package com.teamers.volleybal.domein;


public enum EventType {

    TRAINING("TRAINING"),
    WEDSTRIJD("WEDSTRIJD"),
    TOERNOOI("TOERNOOI"),
    ZAALDIENST("ZAALDIENST"),
    TELLEN("TELLEN"),
    OTHER("OTHER");

    private final String type;


    EventType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
