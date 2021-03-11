package com.rostand.FarmBotWEB.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class CreateReglagesDTO {
    private int frequence_scan;
    private int G1;
    private int G2;
    private int G3;
    private int PostRefOutilX;
    private int PostRefOutilY;
    private int PostRefOutilZ;
}
