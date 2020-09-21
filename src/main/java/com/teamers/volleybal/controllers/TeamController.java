package com.teamers.volleybal.controllers;


import com.teamers.volleybal.domein.Speler;
import com.teamers.volleybal.domein.Team;
import com.teamers.volleybal.repositories.EventRepository;
import com.teamers.volleybal.repositories.TeamRepository;
import com.teamers.volleybal.repositories.TeamledenRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TeamController {

    private TeamledenRepository teamledenRepository;

    public TeamController(TeamledenRepository teamledenRepository, EventRepository eventRepository) {
        this.teamledenRepository = teamledenRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/team/{id}/spelers")
    public List<Speler> showTeamleden(@PathVariable("id") Long teamID) throws IOException {
//        teamledenRepository.addSpelerToSpelersListFromResource(new Speler("extraSpeler", "extra@speler.nl", 12, "buiten"));
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
            return new Team("Team is onbekend");
        }
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
