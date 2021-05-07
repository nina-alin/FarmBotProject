package com.rostand.farmbotapi.Entity;

import lombok.*;

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
}
