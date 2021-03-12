package com.rostand.FarmBotWEBv2.DTO;

import com.rostand.FarmBotWEBv2.Entity.Champ;
import com.rostand.FarmBotWEBv2.Entity.Plante;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class CreatePlantationDTO {
    private int x;
    private int y;
    private Champ champ;
    //private Plante plante;
}
