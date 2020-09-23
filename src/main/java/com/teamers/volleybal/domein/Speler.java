package com.teamers.volleybal.domein;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class Speler {

    private String naam;
    private String email;
    private String rugnummer;
    private String basispositie;
    private String teamID;


    @Override
    public String toString() {
        return "Speler{" +
                "naam='" + naam + '\'' +
                ", email='" + email + '\'' +
                ", basispositie='" + basispositie + '\'' +
                ", rugnummer=" + rugnummer + '\'' +
                '}';
    }


}
