package com.rostand.FarmBotWEB.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Reglages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private int frequence_scan;

    @Column
    private int G1;

    @Column
    private int G2;

    @Column
    private int G3;

    @Column
    private int PostRefOutilX;

    @Column
    private int PostRefOutilY;

    @Column
    private int PostRefOutilZ;
}

