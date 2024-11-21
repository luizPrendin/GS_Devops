package com.api.gs.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AparelhoRequest {
    @NotBlank
    @Size(min = 2, max = 255)
    private String nome;

    @Positive
    @NotNull
    private Long potencia;

    @NotBlank
    @Size(min = 2, max = 255)
    private String tipo;
}
