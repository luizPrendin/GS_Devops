package com.api.gs.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PrecoEnergiaRequest {

    @NotNull
    private String data;

    @Positive
    @NotNull
    private Double precoKwh;
}
