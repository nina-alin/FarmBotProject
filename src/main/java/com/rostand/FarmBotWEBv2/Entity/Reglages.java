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
    private int frequence_scan;

    @OneToOne
    @JoinColumn(name = "plante_id", insertable=false, updatable = false)
    private Plante G1;

    @OneToOne
    @JoinColumn(name = "plante_id", insertable=false, updatable = false)
    private Plante G2;

    @OneToOne
    @JoinColumn(name = "plante_id", insertable=false, updatable = false)
    private Plante G3;

    @Column
    private int PostRefOutilX;

    @Column
    private int PostRefOutilY;

    @Column
    private int PostRefOutilZ;
}

