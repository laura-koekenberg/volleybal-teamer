package com.teamers.volleybal.controllers;


import com.teamers.volleybal.domein.Event;
import com.teamers.volleybal.domein.Speler;
import com.teamers.volleybal.repositories.EventRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventController {

    private EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/team/{id}/events")
    public List<Event> showWedstrijden(@PathVariable("id") Long teamID) {
        if (EventRepository.allEventsList.isEmpty()) {
            eventRepository.fillEventList();
        }

        List<Event> eventList = EventRepository.allEventsList;
        return eventList.stream()
                .filter(event -> event.getThuisteam().getTeamID().equals(teamID) || event.getTegenstander().getTeamID().equals(teamID))
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/team/{id}/wedstrijden")
    public List<Speler> showWedstrijdspelers(@PathVariable("id") Long wedstrijdID) {
        if (EventRepository.allEventsList.isEmpty()) {
            eventRepository.fillEventList();
        }
        List<Event> eventList = EventRepository.allEventsList;
        List<Event> oneEventList = eventList.stream()
                                            .filter(event -> event.getEventID().equals(wedstrijdID)).collect(Collectors.toList());
        if (!oneEventList.isEmpty()) {
            return oneEventList.get(0).getAanwezigeSpelers();
        } else {
            return Collections.emptyList();
        }
    }



//    @PostMapping("/events")
//    public Event voegEventToe(@RequestBody Event event) {
//        return eventRepository.save(event);
//    }
//
//    @PostMapping("/events/team/eventID")
//    public void voegSpelerToeAanBeschikbareSpelers(@RequestBody Event event, Speler speler) {
//        event.voegSpelerToeAanBeschikbareSpelers(event, speler);
//    }
}
