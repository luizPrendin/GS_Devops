package com.api.gs.DTO;

import com.api.gs.model.Aparelho;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ConsumoRequest {
    @NotNull
    private Aparelho aparelho;

    @NotNull
    private String data;

    @Positive
    @NotNull
    private Double consumoKwh;

    @Positive
    @NotNull
    private Double custoEstimado;
}
