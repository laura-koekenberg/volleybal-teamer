package com.teamers.volleybal.domein;

import com.teamers.volleybal.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Event {

    private String eventID;
    private String typeEvent;
    private Team thuisteam;
    private Team tegenstander;
    private String datum;
    private String starttijd;
    private String eindtijd;
    private String weekdag;


    public Event(String eventID, String typeEvent, String teamID, String datum, String starttijd) {
        this.eventID = eventID;
        this.typeEvent = typeEvent;
        this.thuisteam = setThuisteamObvTeamId(teamID);
        this.datum = datum;
        this.starttijd = starttijd;
        this.weekdag = getWeekdag(LocalDate.parse(datum, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        this.eindtijd = getDuur(LocalTime.parse(String.format(starttijd, DateTimeFormatter.ofPattern("HH.MM"))), typeEvent);
    }

    public Event(String eventID, String typeEvent, String teamID, String datum, String starttijd, String teamIDTegenstander) {
        this(eventID, typeEvent, teamID, datum, starttijd);
        this.tegenstander = setTegenstanderObvTeamId(teamIDTegenstander);
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

    public Team setThuisteamObvTeamId(String teamId) {
        List<Team> thuisteams = TeamRepository.alleTeamsList.stream()
                                                            .filter(team -> team.getTeamID().equals(teamId))
                                                            .collect(Collectors.toList());
        return thuisteams.get(0);
    }

    public Team setTegenstanderObvTeamId(String teamId) {
        List<Team> tegenstanders = TeamRepository.alleTeamsList.stream()
                                                               .filter(team -> team.getTeamID().equals(teamId))
                                                               .collect(Collectors.toList());
        return tegenstanders.get(0);
    }
}

