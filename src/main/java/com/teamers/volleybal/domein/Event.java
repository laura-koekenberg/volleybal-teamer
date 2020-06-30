package com.teamers.volleybal.domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventID;
    private EventType typeEvent;
    private String thuisteam;
    private String tegenstander = "nvt";
    private String datum;
    private LocalTime starttijd;
    private LocalTime eindtijd;
    private String weekdag;


    public Event(String typeEvent, String thuisteam, LocalDate datum, LocalTime starttijd) {
        this.typeEvent = getTypeEvent(typeEvent);
        this.thuisteam = thuisteam;
        this.datum = datum.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.weekdag = getWeekdag(datum);
        this.starttijd = starttijd;
        this.eindtijd = getDuur(starttijd, typeEvent);
        createListBeschikbareSpelers();
    }

    public Event(String typeEvent, String thuisteam, LocalDate datum, LocalTime starttijd, String tegenstander) {
        this(typeEvent, thuisteam, datum, starttijd);
        this.weekdag = getWeekdag(datum);
        this.tegenstander = tegenstander;
        createListBeschikbareSpelers();
    }

    public Event(String typeEvent, String thuisteam, LocalDate datum, LocalTime starttijd, LocalTime eindtijd) {
        this(typeEvent, thuisteam, datum, starttijd);
        this.weekdag = getWeekdag(datum);
        this.eindtijd = eindtijd;
        createListBeschikbareSpelers();
    }

    public Long getEventID() {
        return eventID;
    }

    private LocalTime getDuur(LocalTime starttijd, String typeEvent) {
        Long duur;
        if (typeEvent.equalsIgnoreCase("training"))
            duur = 90l;
        else duur = 120l;
        return starttijd.plusMinutes(duur);
    }

    private String getWeekdag(LocalDate datum) {
        weekdag = datum.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("NL"));
        return weekdag;
    }

    private EventType getTypeEvent(String typeEvent) {
        return EnumSet.allOf(EventType.class)
                      .stream()
                      .filter(eventType -> eventType.getType().equalsIgnoreCase(typeEvent))
                      .findFirst()
                      .orElse(EventType.OTHER);
    }

    public List<Speler> createListBeschikbareSpelers() {
        return new ArrayList<>();
    }

    public List<Speler> voegSpelerToeAanBeschikbareSpelers(Event event, Speler speler) {
        List<Speler> beschikbaar = new ArrayList<>();
        beschikbaar.add(speler);
        return beschikbaar;
    }

    @Override
    public String toString() {
        return "Event{" +
                typeEvent +
                ": " + weekdag + " " + datum +
                ", starttijd: " + starttijd +
                ", eindtijd: " + eindtijd +
                ", team: " + thuisteam +
                ", tegenstander: " + tegenstander +
                ", beschikbare spelers: " + createListBeschikbareSpelers() +
                "}";
    }

}

