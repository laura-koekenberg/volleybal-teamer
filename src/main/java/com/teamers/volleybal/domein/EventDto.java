package com.teamers.volleybal.domein;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventDto {

    private final String eventID;
    private final String typeEvent;
    private final Team thuisteam;
    private final String datum;
    private final String starttijd;
    private final Team tegenstander = null;


}
