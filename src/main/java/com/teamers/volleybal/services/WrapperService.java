package com.teamers.volleybal.services;

import com.teamers.volleybal.domein.Event;
import com.teamers.volleybal.domein.EventDto;
import com.teamers.volleybal.domein.Team;

public class WrapperService {


    public static Event wrapEventDtoToEvent(EventDto event) {
        return Event.builder()
                    .eventID(event.getEventID())
                    .typeEvent(event.getTypeEvent())
                    .thuisteam(event.getThuisteam())
                    .datum(event.getDatum())
                    .starttijd(event.getStarttijd())
                    .tegenstander(checkIfTegenstanderAanwezig(event))
                    .build();
    }

    private static Team checkIfTegenstanderAanwezig(EventDto event) {
        if (event.getTypeEvent().equals("WEDSTRIJD")) {
            return event.getTegenstander();
        }
        return null;
    }
}
