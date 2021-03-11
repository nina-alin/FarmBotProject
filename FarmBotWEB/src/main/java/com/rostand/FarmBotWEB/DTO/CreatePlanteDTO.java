package com.rostand.FarmBotWEB.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePlanteDTO {
    private String nom;
    private String description;
    private String description_courte;
    private String icone;
}
