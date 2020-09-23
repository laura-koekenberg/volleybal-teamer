package com.teamers.volleybal.controllers;


import com.teamers.volleybal.domein.Speler;
import com.teamers.volleybal.domein.SpelerDto;
import com.teamers.volleybal.domein.Team;
import com.teamers.volleybal.repositories.EventRepository;
import com.teamers.volleybal.repositories.TeamRepository;
import com.teamers.volleybal.repositories.TeamledenRepository;
import com.teamers.volleybal.services.WrapperService;
import org.springframework.web.bind.annotation.*;

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
        if (TeamledenRepository.spelersPerTeam.isEmpty()) {
            teamledenRepository.fillSpelersOverzicht();
        }
        List<Speler> spelerList = new ArrayList<>();
        Map<Speler, Long> alleLeden = TeamledenRepository.spelersPerTeam;
        alleLeden.forEach((key, value) -> {
            if (value.equals(teamID)) {
                spelerList.add(key);
                spelerList.forEach(speler -> {
                    speler.setTeamID(String.valueOf(teamID));
                });
            }
        });
        return spelerList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/team/teams")
    public List<Team> showTeams() throws IOException {
        TeamRepository.addTeamToTeamsListFromResource(new Team("12", "Bruvoc DS3"));
        return TeamRepository.alleTeamsList;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/team/{id}/")
    public Team getTeam(@PathVariable("id") String teamID) {
        if (TeamRepository.alleTeamsList.isEmpty()) {
            TeamRepository.getTeamListFromResource();
        }
        List<Team> teamsList = TeamRepository.alleTeamsList;
        List<Team> oneTeamList = teamsList.stream()
                                          .filter(team -> team.getTeamID().equals(teamID))
                                          .collect(Collectors.toList());
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

    @PostMapping("/spelers/add")
    public void voegSpelerToe(@RequestBody SpelerDto speler) throws IOException {
        Speler receivedSpeler = WrapperService.wrapSpelerDtoToSpeler(speler);
        teamledenRepository.addSpelerToSpelersListFromResource(receivedSpeler);
    }

}
