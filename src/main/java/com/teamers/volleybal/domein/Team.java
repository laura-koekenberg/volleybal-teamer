package com.teamers.volleybal.domein;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
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
