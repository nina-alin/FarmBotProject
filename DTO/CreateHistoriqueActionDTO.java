package com.rostand.farmbotapi.DTO;

import com.rostand.farmbotapi.Entity.HistoriqueAction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateHistoriqueActionDTO {
    private HistoriqueAction.eTypeAction typeAction;
}
