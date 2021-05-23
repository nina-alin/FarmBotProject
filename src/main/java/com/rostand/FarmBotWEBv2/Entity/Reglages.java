package com.rostand.FarmBotWEBv2.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Reglages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private int frequence_arrosage;

    @Column
    private int frequence_scan;

    @OneToOne
    @JoinColumn(name = "plante_id1")
    private Plante G1;

    @OneToOne
    @JoinColumn(name = "plante_id2")
    private Plante G2;

    @OneToOne
    @JoinColumn(name = "plante_id3")
    private Plante G3;
}

