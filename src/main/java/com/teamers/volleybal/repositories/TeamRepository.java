package com.teamers.volleybal.repositories;

import com.teamers.volleybal.domein.Team;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeamRepository{

    public static List<Team> alleTeamsList = new ArrayList<>();


    public static void fillAlleTeamsList() {
        Team team1 = new Team(1L, "WSV Dames 1");
        Team team2 = new Team(2L, "WSV Dames 2");
        Team team3 = new Team(3L, "Smash Dames 1");
        Team team4 = new Team(4L, "Smash Dames 3");
        Team team5 = new Team(5L, "Boemerang Dames 1");
        Team team6 = new Team(6L, "Boemerang Heren 1");
        Team team7 = new Team(7L, "Blok'74 Heren 2");
        alleTeamsList.add(team1);
        alleTeamsList.add(team2);
        alleTeamsList.add(team3);
        alleTeamsList.add(team4);
        alleTeamsList.add(team5);
        alleTeamsList.add(team6);
        alleTeamsList.add(team7);
    }


        }
