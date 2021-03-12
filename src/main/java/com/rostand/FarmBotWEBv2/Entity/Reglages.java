package com.rostand.FarmBotWEBv2.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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

