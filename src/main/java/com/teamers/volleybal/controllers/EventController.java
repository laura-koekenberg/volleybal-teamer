package com.teamers.volleybal.controllers;


import com.teamers.volleybal.domein.Event;
import com.teamers.volleybal.domein.EventDto;
import com.teamers.volleybal.domein.Speler;
import com.teamers.volleybal.repositories.EventRepository;
import com.teamers.volleybal.services.WrapperService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/team/{id}/events")
    public List<Event> showWedstrijden(@PathVariable("id") String teamID) {
        List<Event> eventList = EventRepository.allEventsList;

        return eventList.stream()
                        .filter(event -> event.getThuisteam().getTeamID().equals(teamID) ||
                                event.getTegenstander().getTeamID().equals(teamID))
                        .collect(Collectors.toList());
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping("/team/{id}/wedstrijden")
//    public List<Speler> showWedstrijdspelers(@PathVariable("id") String wedstrijdID) {
//        List<Event> eventList = EventRepository.allEventsList;
//        List<Event> oneEventList = eventList.stream()
//                                            .filter(event -> event.getEventID().equals(wedstrijdID))
//                                            .collect(Collectors.toList());
//        if (!oneEventList.isEmpty()) {
//            return oneEventList.get(0).getAanwezigeSpelers();
//        } else {
//            return Collections.emptyList();
//        }
//    }

    @PostMapping("/events/add")
    public void voegEventToe(@RequestBody EventDto event) throws IOException {
        Event receivedEvent = WrapperService.wrapEventDtoToEvent(event);
        EventRepository.addEventToEventListFromResource(receivedEvent);
    }

//    @PostMapping("/events/team/eventID")
//    public void voegSpelerToeAanBeschikbareSpelers(@RequestBody Event event, Speler speler) {
//        event.voegSpelerToeAanBeschikbareSpelers(event, speler);
//    }
}
