package com.teamers.volleybal.repositories;

import com.teamers.volleybal.domein.Team;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Repository
public class TeamRepository {

    public static List<Team> alleTeamsList = getTeamListFromResource();


//    public static void fillAlleTeamsList() {
//        Team team1 = new Team("1", "WSV Dames 1");
//        Team team2 = new Team("2", "WSV Dames 2");
//        Team team3 = new Team("3", "Smash Dames 1");
//        Team team4 = new Team("4", "Smash Dames 3");
//        Team team5 = new Team("5", "Boemerang Dames 1");
//        Team team6 = new Team("6", "Boemerang Heren 1");
//        Team team7 = new Team("7", "Blok'74 Heren 2");
//        alleTeamsList.add(team1);
//        alleTeamsList.add(team2);
//        alleTeamsList.add(team3);
//        alleTeamsList.add(team4);
//        alleTeamsList.add(team5);
//        alleTeamsList.add(team6);
//        alleTeamsList.add(team7);
//    }

    public static List<Team> getTeamListFromResource() {
        List<Team> teamList = new ArrayList<>();
        try (final Scanner scanner = new Scanner(new File("teams.txt"))) {
            while (scanner.hasNext()) {
                List<String> list = Arrays.asList(scanner.nextLine().split(", "));
                Team teamFromFile = createTeamFromResource(list);
                teamList.add(teamFromFile);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return teamList;
    }

    private static Team createTeamFromResource(List<String> list) {
        return new Team(list.get(0), list.get(1));
    }

    public static void addTeamToTeamsListFromResource(Team team) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("teams.txt", true));
        writer.append(team.getTeamID()).append(", ")
              .append(team.getTeamnaam()).append("\n");
        writer.close();
        alleTeamsList = getTeamListFromResource();
    }

}
