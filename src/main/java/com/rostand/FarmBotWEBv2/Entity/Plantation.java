package com.rostand.FarmBotWEBv2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Plantation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    int x;

    @Column
    int y;

    @Column(name = "timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Timestamp datePlantation;

    @ManyToOne
    @JsonIgnore
    private Champ champ;

    @OneToOne(optional = true)
    @JoinColumn(name = "plante_id")
    private Plante plante;
}
