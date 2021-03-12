package com.rostand.FarmBotWEBv2.Entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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
