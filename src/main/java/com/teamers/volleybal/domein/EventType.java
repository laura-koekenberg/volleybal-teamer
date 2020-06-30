package com.teamers.volleybal.domein;


public enum EventType {

    TRAINING("training"),
    WEDSTRIJD("wedstrijd"),
    TOERNOOI("toernooi"),
    ZAALDIENST("zaaldienst"),
    TELLEN("tellen"),
    OTHER("other");

    private final String type;


    EventType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
