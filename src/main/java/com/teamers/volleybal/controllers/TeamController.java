package com.teamers.volleybal.controllers;


import com.teamers.volleybal.domein.Event;
import com.teamers.volleybal.domein.Speler;
import com.teamers.volleybal.domein.Team;
import com.teamers.volleybal.repositories.EventRepository;
import com.teamers.volleybal.repositories.TeamRepository;
import com.teamers.volleybal.repositories.TeamledenRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TeamController {

    private TeamledenRepository teamledenRepository;
    private EventRepository eventRepository;

    public TeamController(TeamledenRepository teamledenRepository, EventRepository eventRepository) {
        this.teamledenRepository = teamledenRepository;
        this.eventRepository = eventRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/team/{id}/spelers")
    public List<Speler> showTeamleden(@PathVariable("id") Long teamID) {
        if (TeamledenRepository.spelersPerTeam.isEmpty()) {
            teamledenRepository.fillSpelersOverzicht();
        }
        List<Speler> spelerList = new ArrayList<>();
        Map<Speler, Long> alleLeden = TeamledenRepository.spelersPerTeam;
        alleLeden.forEach((key, value) -> {
            if (value.equals(teamID)) {
                spelerList.add(key);
                spelerList.forEach(speler -> {
                    speler.setTeamID(teamID);
                });
            }
        });
        return spelerList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/team/teams")
    public List<Team> showTeams() {
        if (TeamRepository.alleTeamsList.isEmpty()) {
            TeamRepository.fillAlleTeamsList();
        }
        return TeamRepository.alleTeamsList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/team/{id}/")
    public Team getTeam(@PathVariable("id") Long teamID) {
        if (TeamRepository.alleTeamsList.isEmpty()) {
            TeamRepository.fillAlleTeamsList();
        }
        List<Team> teamsList = TeamRepository.alleTeamsList;
        List<Team> oneTeamList = teamsList.stream()
                                          .filter(team -> team.getTeamID().equals(teamID)).collect(Collectors.toList());
        if (!oneTeamList.isEmpty()) {
            return oneTeamList.get(0);
        } else {
            return new Team("Teamnaam does not exist");
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/team/{id}/events")
    public List<Event> showWedstrijden(@PathVariable("id") Long teamID) {
        if (EventRepository.allEventsList.isEmpty()) {
            eventRepository.fillEventList();
        }
        return EventRepository.allEventsList;
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


//    @GetMapping("/team/{teamID}/events")
//    public Optional<Event> showEvents(@PathVariable Long teamID) {
//        return eventRepository.findById(teamID);
//    }

//    @PostMapping("/teams")
//    public Team maakTeam(@RequestBody Team team) {
//        return teamRepository.save(team);
//    }

//    @PostMapping("/team/{teamId}/speler")
//    public Speler voegSpelerToe(@RequestBody Speler speler) {
//        return teamledenRepository.save(speler);
//    }

}
