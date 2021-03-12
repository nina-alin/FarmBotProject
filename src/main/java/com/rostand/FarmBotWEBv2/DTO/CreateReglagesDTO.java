package com.rostand.FarmBotWEBv2.DTO;

import com.rostand.FarmBotWEBv2.Entity.Plante;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class CreateReglagesDTO {
    private int frequence_scan;
    private Long G1;
    private Long G2;
    private Long G3;
    private int PostRefOutilX;
    private int PostRefOutilY;
    private int PostRefOutilZ;
}
