package com.rostand.FarmBotWEBv2.DTO;

import com.rostand.FarmBotWEBv2.Entity.Champ;
import com.rostand.FarmBotWEBv2.Entity.Plante;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.query.Param;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class CreatePlantationDTO {
    @Min(1)
    @Max(6)
    private int x;

    @Min(1)
    @Max(3)
    private int y;

    private Long planteId;
}
