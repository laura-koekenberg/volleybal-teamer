package com.teamers.volleybal.domein;

public class Speler {


    private Long spelerID;
    private String naam;
    private int rugnummer;
    private String email;
    private String basispositie;
    private String profielfoto;
    private Long teamID;


    public Long getSpelerID() {
        return spelerID;
    }

    public void setSpelerID(Long spelerID) {
        this.spelerID = spelerID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBasispositie() {
        return basispositie;
    }

    public void setBasispositie(String basispositie) {
        this.basispositie = basispositie;
    }

    public int getRugnummer() {
        return rugnummer;
    }

    public void setRugnummer(int rugnummer) {
        this.rugnummer = rugnummer;
    }

    public String getProfielfoto() {
        return profielfoto;
    }

    public void setProfielfoto(String profielfoto) {
        this.profielfoto = profielfoto;
    }

    public Long getTeamID() {
        return teamID;
    }

    public void setTeamID(Long teamID) {
        this.teamID = teamID;
    }

    public Speler() {
    }

    public Speler(String naam, String email) {
        this.naam = naam;
        this.email = email;
    }

    public Speler(String naam, String email, int rugnummer) {
        this(naam, email);
        this.rugnummer = rugnummer;
    }

    public Speler(String naam, String email, int rugnummer, String basispositie) {
        this(naam, email, rugnummer);
        this.basispositie = basispositie;
    }

    public Speler(String naam, String email, int rugnummer, String basispositie, String profielfoto) {
        this(naam, email, rugnummer, basispositie);
        this.profielfoto = profielfoto;
    }

    @Override
    public String toString() {
        return "Speler{" +
                "naam='" + naam + '\'' +
                ", email='" + email + '\'' +
                ", basispositie='" + basispositie + '\'' +
                ", rugnummer=" + rugnummer + '\'' +
                ", profielfoto=" + profielfoto +
                ", teamId=" + teamID +
                '}';
    }


}
