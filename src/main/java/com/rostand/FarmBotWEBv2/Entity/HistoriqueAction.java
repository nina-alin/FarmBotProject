package com.rostand.FarmBotWEBv2.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Entity
public class HistoriqueAction {

    public enum eTypeAction {
            ARROSAGE,
            SCAN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    // l'action peut être de type arrosage ou de type scan des mauvaises herbes
    @Column(nullable = false)
    eTypeAction typeAction;

    // si la dernière action remonte à par exemple 5 heures, alors un nouvel arrosage automatique se lance
    @Column(name = "timestamp", nullable = false)
    Timestamp dateAction = new Timestamp(new Date().getTime());
}
