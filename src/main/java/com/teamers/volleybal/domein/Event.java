package com.teamers.volleybal.domein;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Getter
public class Event {

    private Long eventID;
    private String typeEvent;
    private Team thuisteam;
    private Team tegenstander;
    private String datum;
    private String weekdag;
    private String starttijd;
    private String eindtijd;
    private List<Speler> aanwezigeSpelers;


    public Event(Long eventID, String typeEvent, Team thuisteam, LocalDate datum, String starttijd, List<Speler> aanwezigeSpelers) {
        this.eventID = eventID;
        this.typeEvent = typeEvent;
        this.thuisteam = thuisteam;
        this.starttijd = starttijd;
        this.aanwezigeSpelers = aanwezigeSpelers;
        this.datum = datum.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.weekdag = getWeekdag(datum);
        this.tegenstander = new Team("nvt");
        this.eindtijd = getDuur(LocalTime.parse(String.format(starttijd, DateTimeFormatter.ofPattern("HH.MM"))), typeEvent);
    }

    public Event(Long eventID, String typeEvent, Team thuisteam, LocalDate datum, String starttijd, List<Speler> aanwezigeSpelers, Team tegenstander ) {
        this(eventID, typeEvent, thuisteam, datum, starttijd, aanwezigeSpelers);
        this.tegenstander = tegenstander;
        this.weekdag = getWeekdag(datum);
        this.eindtijd = getDuur(LocalTime.parse(String.format(starttijd, DateTimeFormatter.ofPattern("HH.MM"))), typeEvent);
    }

    private String getDuur(LocalTime starttijd, String typeEvent) {
        long duur;
        if (typeEvent.equalsIgnoreCase("training"))
            duur = 90L;
        else duur = 120L;
        return starttijd.plusMinutes(duur).toString();
    }

    private String getWeekdag(LocalDate datum) {
        weekdag = datum.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("NL"));
        return weekdag;
    }
//
//    private EventType getTypeEvent(String typeEvent) {
//        return EnumSet.allOf(EventType.class)
//                      .stream()
//                      .filter(eventType -> eventType.getType().equalsIgnoreCase(typeEvent))
//                      .findFirst()
//                      .orElse(EventType.OTHER);
//    }
//
//    public List<Speler> voegSpelerToeAanBeschikbareSpelers(Event event, Speler speler) {
//        List<Speler> beschikbaar = new ArrayList<>();
//        beschikbaar.add(speler);
//        return beschikbaar;
//    }

    @Override
    public String toString() {
        return "Event{" +
                typeEvent +
                ": " + weekdag + " " +
                ", starttijd: " + starttijd +
                ", eindtijd: " + eindtijd +
                ", team: " + thuisteam +
                ", tegenstander: " + tegenstander +
                ", beschikbare spelers: " +
                "}";
    }

}

