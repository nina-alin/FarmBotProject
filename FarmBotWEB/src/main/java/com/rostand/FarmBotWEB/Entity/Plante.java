package com.rostand.FarmBotWEB.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Plante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String nom;

    @Column
    String description;

    @Column
    String description_courte;

    @Column
    String icone;
}
