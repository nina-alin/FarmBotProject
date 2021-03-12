package com.rostand.FarmBotWEBv2.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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
