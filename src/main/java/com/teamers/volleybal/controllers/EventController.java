package com.teamers.volleybal.controllers;


import com.teamers.volleybal.domein.Event;
import com.teamers.volleybal.domein.Speler;
import com.teamers.volleybal.domein.Team;
import com.teamers.volleybal.repositories.EventRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    private EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    private Team wsv = new Team("WSV");
    private Speler speler1 = new Speler("Laura", "laura.koekenberg@gmail.com", 9, "mid", "www.instagram.com");
    private Speler speler2 = new Speler("Jeroen", "jeroen.email@gmail.com", 12, "spelverdeler");
    private Event training = new Event("training", wsv.getTeamnaam(), LocalDate.of(2019, 9, 10), LocalTime.of(20, 00));
    private Event wedstrijd = new Event("wedstrijd", wsv.getTeamnaam(), LocalDate.of(2019, 9, 19), LocalTime.of(19, 00),"smash");
    private Event toernooi = new Event("toernooi", wsv.getTeamnaam(), LocalDate.of(2019, 9, 30), LocalTime.of(9, 00), LocalTime.of(17, 00));

    @GetMapping("/events/teams")
    public String showAllEvents() {
        return training.toString() + wedstrijd.toString() + toernooi.toString();
    }

    @GetMapping("/events/{teamID}")
    public Optional<Event> showEventsByTeam(@PathVariable Long teamID) {
        return eventRepository.findById(teamID);
    }

    @PostMapping("/events")
    public Event voegEventToe(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @GetMapping("/events/team/{eventID}")
    public List<Speler> geefBeschikbareSpelersEvent(@PathVariable Long eventID) {
        Optional<Event> event = eventRepository.findById(eventID);
        return event.get().createListBeschikbareSpelers();
    }

    @PostMapping("/events/team/eventID")
    public void voegSpelerToeAanBeschikbareSpelers(@RequestBody Event event, Speler speler) {
        event.voegSpelerToeAanBeschikbareSpelers(event, speler);
    }
}
