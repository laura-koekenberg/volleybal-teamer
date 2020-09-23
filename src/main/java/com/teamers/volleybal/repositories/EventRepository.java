package com.teamers.volleybal.repositories;

import com.teamers.volleybal.domein.Event;
import com.teamers.volleybal.domein.EventType;
import com.teamers.volleybal.domein.Speler;
import com.teamers.volleybal.domein.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Repository
@Slf4j
public class EventRepository {

    public static List<Event> allEventsList = getEventListFromResource();
    public static List<Team> availableTeamsList = TeamRepository.alleTeamsList;
    public static List<Speler> allAvailableSpelers = TeamledenRepository.getSpelersListFromResource();


//    public void fillEventList() {
//        TeamRepository.getTeamListFromResource();
//        Event event1 = new Event("1", "WEDSTRIJD", availableTeamsList.get(0), LocalDate.of(2020, 9, 2), "14:30", getSpelersEvent1(), availableTeamsList.get(2));
//        Event event2 = new Event("2", "WEDSTRIJD", availableTeamsList.get(2), LocalDate.of(2020, 9, 12), "12:30", getSpelersEvent2(), availableTeamsList.get(4));
//        Event event3 = new Event("3", "TRAINING", availableTeamsList.get(0), LocalDate.of(2020, 9, 24), "19:30", getSpelersEvent3());
//        allEventsList.add(event1);
//        allEventsList.add(event2);
//        allEventsList.add(event3);
//    }
//
//    private List<Speler> getSpelersEvent1() {
//        List<Speler> availableEvent1 = new ArrayList<>();
//        availableEvent1.add(allAvailableSpelers.get(0));
//        availableEvent1.add(allAvailableSpelers.get(2));
//        availableEvent1.add(allAvailableSpelers.get(4));
//        availableEvent1.add(allAvailableSpelers.get(6));
//        availableEvent1.add(allAvailableSpelers.get(8));
//        return availableEvent1;
//    }
//
//    private List<Speler> getSpelersEvent2() {
//        List<Speler> availableEvent2 = new ArrayList<>();
//        availableEvent2.add(allAvailableSpelers.get(1));
//        availableEvent2.add(allAvailableSpelers.get(3));
//        availableEvent2.add(allAvailableSpelers.get(5));
//        availableEvent2.add(allAvailableSpelers.get(7));
//        return availableEvent2;
//    }
//
//    private List<Speler> getSpelersEvent3() {
//        List<Speler> availableEvent3 = new ArrayList<>();
//        availableEvent3.add(allAvailableSpelers.get(0));
//        availableEvent3.add(allAvailableSpelers.get(1));
//        availableEvent3.add(allAvailableSpelers.get(2));
//        availableEvent3.add(allAvailableSpelers.get(3));
//        availableEvent3.add(allAvailableSpelers.get(4));
//        availableEvent3.add(allAvailableSpelers.get(5));
//        availableEvent3.add(allAvailableSpelers.get(6));
//        return availableEvent3;
//    }

    public static List<Event> getEventListFromResource() {
        List<Event> eventList = new ArrayList<>();
        try (final Scanner scanner = new Scanner(new File("events.txt"))) {
            while (scanner.hasNext()) {
                List<String> list = Arrays.asList(scanner.nextLine().split(", "));
                Event eventFromFile = createEventFromResource(list);
                eventList.add(eventFromFile);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return eventList;
    }

    private static Event createEventFromResource(List<String> list) {
        Event eventFromResource = new Event(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
        log.info("eventtype: " + eventFromResource.getTypeEvent());
        if (eventFromResource.getTypeEvent().equals(EventType.WEDSTRIJD.name())) {
            Team tegenstander = eventFromResource.setTegenstanderObvTeamId(list.get(5));
            eventFromResource.setTegenstander(tegenstander);
            return eventFromResource;
        }
        eventFromResource.setTegenstander(new Team("n/a","n/a"));
        return eventFromResource;
    }

    public static void addEventToEventListFromResource(Event event) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt", true));
        writer.append(event.getEventID()).append(", ")
              .append(event.getTypeEvent()).append(", ")
              .append(event.getThuisteam().getTeamID()).append(", ")
              .append(event.getDatum()).append(", ")
              .append(event.getStarttijd()).append(", ");
        if (event.getTypeEvent().equals(EventType.WEDSTRIJD.name())) {
            writer.append(event.getTegenstander().getTeamID()).append("\n");
        } else {
            writer.append("\n");
        }
        writer.close();
        allEventsList = getEventListFromResource();
    }
}
