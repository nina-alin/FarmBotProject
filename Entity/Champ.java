package com.rostand.farmbotapi.Entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Champ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String nom;

    @OneToMany(mappedBy = "champ")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Plantation> plantations = new ArrayList<>();
}
