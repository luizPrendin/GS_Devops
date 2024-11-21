package com.api.gs.DTO;

import lombok.Data;
import org.springframework.hateoas.Link;

@Data
public class AparelhoResponse {
    private Long idAparelho;
    private String nome;
    private Long potencia;
    private String tipo;
    private Link link;

}
