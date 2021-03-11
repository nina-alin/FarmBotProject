package com.rostand.FarmBotWEB.DTO;

import com.rostand.FarmBotWEB.Entity.Champ;
import com.rostand.FarmBotWEB.Entity.Plante;
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
}
