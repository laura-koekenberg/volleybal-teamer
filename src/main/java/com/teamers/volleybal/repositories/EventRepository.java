package com.teamers.volleybal.repositories;

import com.teamers.volleybal.domein.Event;
import com.teamers.volleybal.domein.Speler;
import com.teamers.volleybal.domein.Team;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository {

    public static List<Event> allEventsList = new ArrayList<>();
    public static List<Team> availableTeamsList = TeamRepository.alleTeamsList;
    public static List<Speler> allAvailableSpelers = TeamledenRepository.getSpelersListFromResource();


    public void fillEventList() {
        TeamRepository.getTeamListFromResource();
        Event event1 = new Event(1L, "WEDSTRIJD", availableTeamsList.get(0), LocalDate.of(2020, 9, 2), "14:30", getSpelersEvent1(), availableTeamsList.get(2));
        Event event2 = new Event(2L, "WEDSTRIJD", availableTeamsList.get(2), LocalDate.of(2020, 9, 12), "12:30", getSpelersEvent2(), availableTeamsList.get(4));
        Event event3 = new Event(3L, "TRAINING", availableTeamsList.get(0), LocalDate.of(2020, 9, 24), "19:30", getSpelersEvent3());
        allEventsList.add(event1);
        allEventsList.add(event2);
        allEventsList.add(event3);
    }

    private List<Speler> getSpelersEvent1() {
        List<Speler> availableEvent1 = new ArrayList<>();
        availableEvent1.add(allAvailableSpelers.get(0));
        availableEvent1.add(allAvailableSpelers.get(2));
        availableEvent1.add(allAvailableSpelers.get(4));
        availableEvent1.add(allAvailableSpelers.get(6));
        availableEvent1.add(allAvailableSpelers.get(8));
        return availableEvent1;
    }

    private List<Speler> getSpelersEvent2() {
        List<Speler> availableEvent2 = new ArrayList<>();
        availableEvent2.add(allAvailableSpelers.get(1));
        availableEvent2.add(allAvailableSpelers.get(3));
        availableEvent2.add(allAvailableSpelers.get(5));
        availableEvent2.add(allAvailableSpelers.get(7));
        return availableEvent2;
    }

    private List<Speler> getSpelersEvent3() {
        List<Speler> availableEvent3 = new ArrayList<>();
        availableEvent3.add(allAvailableSpelers.get(0));
        availableEvent3.add(allAvailableSpelers.get(1));
        availableEvent3.add(allAvailableSpelers.get(2));
        availableEvent3.add(allAvailableSpelers.get(3));
        availableEvent3.add(allAvailableSpelers.get(4));
        availableEvent3.add(allAvailableSpelers.get(5));
        availableEvent3.add(allAvailableSpelers.get(6));
        return availableEvent3;
    }
}
