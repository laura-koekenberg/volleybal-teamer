package com.teamers.volleybal.repositories;

import com.teamers.volleybal.domein.Speler;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Math.random;

@Repository
public class TeamledenRepository {

    public static Map<Speler, Long> spelersPerTeam = new HashMap<>();

    public void fillSpelersOverzicht() {
//        List<Speler> spelerList = getSpelers();
        List<Speler> spelerListFromFile = getSpelersListFromResource();
        for (int index = 0; index < spelerListFromFile.size(); index++) {
            Long teamId = (long) ((random() * 4L) + 1L);
            spelersPerTeam.put(spelerListFromFile.get(index), teamId);
        }
    }

//    public static List<Speler> getSpelers() {
//        List<Speler> spelerList = new ArrayList<>();
//        Speler speler1 = new Speler("Marieke", "marieke@wsv.nl", 6, "MIDDEN");
//        Speler speler2 = new Speler("Ans", "ans@wsv.nl", 5, "BUITEN");
//        Speler speler3 = new Speler("Sandra", "sandra@wsv.nl", 8, "SPELVERDELER");
//        Speler speler4 = new Speler("Maya", "maya@wsv.nl", 9, "DIAGONAAL");
//        Speler speler5 = new Speler("Bianca", "bianca@wsv.nl", 20, "BUITEN");
//        Speler speler6 = new Speler("Petra", "petra@wsv.nl", 1, "BUITEN");
//        Speler speler7 = new Speler("Jet", "jet@wsv.nl", 12, "MIDDEN");
//        Speler speler8 = new Speler("Laura", "laura@wsv.nl", 16, "DIAGONAAL");
//        Speler speler9 = new Speler("Mariska K", "mariskaK@wsv.nl", 23, "MIDDEN");
//        Speler speler10 = new Speler("Nienke", "nienke@wsv.nl", 2, "BUITEN");
//        Speler speler11 = new Speler("Poppy", "poppy@wsv.nl", 5, "MIDDEN");
//        Speler speler12 = new Speler("Linda", "linda@wsv.nl", 13, "BUITEN");
//        Speler speler13 = new Speler("Mariska P", "mariskaP@wsv.nl", 7, "DIAGONAAL");
//        Speler speler14 = new Speler("Ruth", "ruth@wsv.nl", 66, "BUITEN");
//        Speler speler15 = new Speler("Rianne", "rianne@wsv.nl", 15, "MIDDEN");
//        Speler speler16 = new Speler("Ellen", "ellen@wsv.nl", 7, "SPELVERDELER");
//        Speler speler17 = new Speler("Harry", "harry@wsv.nl", 9, "BUITEN");
//        Speler speler18 = new Speler("Sjoerd", "sjoerd@wsv.nl", 34, "MIDDEN");
//        spelerList.add(speler1);
//        spelerList.add(speler2);
//        spelerList.add(speler3);
//        spelerList.add(speler4);
//        spelerList.add(speler5);
//        spelerList.add(speler6);
//        spelerList.add(speler7);
//        spelerList.add(speler8);
//        spelerList.add(speler9);
//        spelerList.add(speler10);
//        spelerList.add(speler11);
//        spelerList.add(speler12);
//        spelerList.add(speler13);
//        spelerList.add(speler14);
//        spelerList.add(speler15);
//        spelerList.add(speler16);
//        spelerList.add(speler17);
//        spelerList.add(speler18);
//        return spelerList;
//    }

    public static List<Speler> getSpelersListFromResource() {
        List<Speler> spelerList = new ArrayList<>();
        try (final Scanner scanner = new Scanner(new File("spelers.txt"))) {
            while (scanner.hasNext()) {
                List<String> list = Arrays.asList(scanner.nextLine().split(", "));
                Speler spelerFromFile = createSpelerFromResource(list);
                spelerList.add(spelerFromFile);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return spelerList;
    }

    private static Speler createSpelerFromResource(List<String> list) {
        Speler spelerFromFile = new Speler();
        spelerFromFile.setNaam(list.get(0));
        spelerFromFile.setEmail(list.get(1));
        spelerFromFile.setRugnummer(list.get(2));
        spelerFromFile.setBasispositie(list.get(3));
        return spelerFromFile;
    }

    public void addSpelerToSpelersListFromResource(Speler speler) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("spelers.txt",true));
        writer.append(speler.getNaam()).append(", ")
              .append(speler.getEmail()).append(", ")
              .append(String.valueOf(speler.getRugnummer())).append(", ")
              .append(speler.getBasispositie().toUpperCase()).append("\n");
        writer.close();
        fillSpelersOverzicht();
    }
}
