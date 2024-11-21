package com.api.gs.DTO;

import lombok.Data;
import org.springframework.hateoas.Link;

@Data
public class PrecoEnergiaResponse {
    private Long idPrecoEnergia;
    private String data;
    private Double precoKwh;
    private Link link;
}
