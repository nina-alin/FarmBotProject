package com.rostand.FarmBotWEBv2.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    // l'action peut être de type arrosage ou de type scan des mauvaises herbes
    @Column
    String typeAction;

    // si la dernière action remonte à par exemple 5 minutes, alors un nouvel arrosage automatique se lance
    @Column(name = "timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Timestamp dateAction;
}
