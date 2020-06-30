package com.teamers.volleybal.repositories;

import com.teamers.volleybal.domein.Speler;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.random;

@Repository
public class TeamledenRepository {

    public static Map<Speler, Long> spelersPerTeam = new HashMap<>();

    public void fillSpelersOverzicht() {
        List<Speler> spelerList = getSpelers();

        for (int index = 0; index < 16; index++) {
            Long teamId = (long) ((random() * 4L) + 1L);
            spelersPerTeam.put(spelerList.get(index), teamId);
        }
    }

    private List<Speler> getSpelers() {
        List<Speler> spelerList = new ArrayList<>();
        Speler speler1 = new Speler("Marieke", "marieke@wsv.nl", 6, "MIDDEN");
        Speler speler2 = new Speler("Ans", "ans@wsv.nl", 5, "BUITEN");
        Speler speler3 = new Speler("Sandra", "sandra@wsv.nl", 8, "SPELVERDELER");
        Speler speler4 = new Speler("Maya", "maya@wsv.nl", 9, "DIAGONAAL");
        Speler speler5 = new Speler("Bianca", "bianca@wsv.nl", 20, "BUITEN");
        Speler speler6 = new Speler("Petra", "petra@wsv.nl", 1, "BUITEN");
        Speler speler7 = new Speler("Jet", "jet@wsv.nl", 12, "MIDDEN");
        Speler speler8 = new Speler("Laura", "laura@wsv.nl", 16, "DIAGONAAL");
        Speler speler9 = new Speler("Mariska K", "mariskaK@wsv.nl", 23, "MIDDEN");
        Speler speler10 = new Speler("Nienke", "nienke@wsv.nl", 6, "BUITEN");
        Speler speler11 = new Speler("Poppy", "poppy@wsv.nl", 6, "MIDDEN");
        Speler speler12 = new Speler("Linda", "linda@wsv.nl", 6, "BUITEN");
        Speler speler13 = new Speler("Mariska P", "mariskaP@wsv.nl", 6, "DIAGONAAL");
        Speler speler14 = new Speler("Ruth", "ruth@wsv.nl", 6, "BUITEN");
        Speler speler15 = new Speler("Rianne", "rianne@wsv.nl", 6, "MIDDEN");
        Speler speler16 = new Speler("Ellen", "ellen@wsv.nl", 6, "SPELVERDELER");
        spelerList.add(speler1);
        spelerList.add(speler2);
        spelerList.add(speler3);
        spelerList.add(speler4);
        spelerList.add(speler5);
        spelerList.add(speler6);
        spelerList.add(speler7);
        spelerList.add(speler8);
        spelerList.add(speler9);
        spelerList.add(speler10);
        spelerList.add(speler11);
        spelerList.add(speler12);
        spelerList.add(speler13);
        spelerList.add(speler14);
        spelerList.add(speler15);
        spelerList.add(speler16);
        return spelerList;


    }
}
