package com.rostand.FarmBotWEBv2.DTO;

import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class CreatePlantationDTO {
    @Min(1)
    @Max(6)
    private Integer x;

    @Min(1)
    @Max(3)
    private Integer y;

    @Nullable
    private Long planteId;
}
