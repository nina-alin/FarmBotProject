package com.rostand.FarmBotWEBv2.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Monitor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
}
