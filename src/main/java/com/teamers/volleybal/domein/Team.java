package com.teamers.volleybal.domein;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Team {

    private String teamID;
    private String teamnaam;

    public Team(String teamnaam) {
        this.teamnaam = teamnaam;
    }

    public Team(String teamID, String teamnaam) {
        this.teamID = teamID;
        this.teamnaam = teamnaam;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamnaam='" + teamnaam +
                "teamID='" + teamID +
                '}';
    }
}
