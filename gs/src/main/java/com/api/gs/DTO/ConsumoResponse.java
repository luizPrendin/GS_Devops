package com.api.gs.DTO;

import com.api.gs.model.Aparelho;
import lombok.Data;
import org.springframework.hateoas.Link;

@Data
public class ConsumoResponse {
    private Long idConsumo;
    private Aparelho aparelho;
    private String data;
    private Double consumoKwh;
    private Double custoEstimado;
    private Link link;
}
