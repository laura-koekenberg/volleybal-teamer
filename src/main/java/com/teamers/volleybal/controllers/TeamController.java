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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TeamController {

    private TeamRepository teamRepository;
    private TeamledenRepository teamledenRepository;
    private EventRepository eventRepository;

    public TeamController(TeamRepository teamRepository, TeamledenRepository teamledenRepository, EventRepository eventRepository) {
        this.teamRepository = teamRepository;
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
            teamRepository.fillAlleTeamsList();
        }
        return TeamRepository.alleTeamsList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/team/{id}/")
    public String getTeam(@PathVariable("id") Long teamID) {
        if (TeamRepository.alleTeamsList.isEmpty()) {
            teamRepository.fillAlleTeamsList();
        }
        List<Team> teamsList = TeamRepository.alleTeamsList;
        Optional<String> teamnaam = teamsList.stream().filter(team -> teamID.equals(team.getTeamID())).findFirst().map(Team::getTeamnaam);
        return teamnaam.orElse("Teamnaam does not exist");
    }

    @GetMapping("/team/{teamID}/events")
    public Optional<Event> showEvents(@PathVariable Long teamID) {
        return eventRepository.findById(teamID);
    }

//    @PostMapping("/teams")
//    public Team maakTeam(@RequestBody Team team) {
//        return teamRepository.save(team);
//    }

//    @PostMapping("/team/{teamId}/speler")
//    public Speler voegSpelerToe(@RequestBody Speler speler) {
//        return teamledenRepository.save(speler);
//    }

}
