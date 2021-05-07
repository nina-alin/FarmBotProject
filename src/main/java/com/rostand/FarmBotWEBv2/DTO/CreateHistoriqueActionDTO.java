package com.rostand.FarmBotWEBv2.DTO;

import com.rostand.FarmBotWEBv2.Entity.HistoriqueAction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateHistoriqueActionDTO {
    private HistoriqueAction.eTypeAction typeAction;
}
