package com.rostand.FarmBotWEBv2.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReglagesDTO {
    private int frequence_scan;
    private int frequence_arrosage;
    private Long G1;
    private Long G2;
    private Long G3;
}
