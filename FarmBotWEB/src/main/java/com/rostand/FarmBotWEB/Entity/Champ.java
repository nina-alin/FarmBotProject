package com.rostand.FarmBotWEB.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Champ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String nom;

   /* @OneToMany(mappedBy = "champ")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Plantation> plantations = new ArrayList<>();*/
}
