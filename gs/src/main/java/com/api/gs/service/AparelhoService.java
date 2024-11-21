package com.api.gs.service;

import com.api.gs.DTO.AparelhoRequest;
import com.api.gs.DTO.AparelhoResponse;
import com.api.gs.model.Aparelho;
import org.springframework.stereotype.Service;

@Service
public class AparelhoService {

    public Aparelho requestToAparelho(AparelhoRequest aparelhoRequest) {
        Aparelho aparelho = new Aparelho();
        aparelho.setNome(aparelhoRequest.getNome());
        aparelho.setPotencia(aparelhoRequest.getPotencia());
        aparelho.setTipo(aparelhoRequest.getTipo());

        return aparelho;
    }

    public AparelhoResponse aparelhoToResponse(Aparelho aparelho) {
        AparelhoResponse aparelhoResponse = new AparelhoResponse();
        aparelhoResponse.setIdAparelho(aparelho.getIdAparelho());
        aparelhoResponse.setNome(aparelho.getNome());
        aparelhoResponse.setPotencia(aparelho.getPotencia());
        aparelhoResponse.setTipo(aparelho.getTipo());

        return aparelhoResponse;
    }

    public AparelhoRequest aparelhoToRequest(Aparelho aparelho) {
        AparelhoRequest aparelhoRequest = new AparelhoRequest();
        aparelhoRequest.setNome(aparelho.getNome());
        aparelhoRequest.setPotencia(aparelho.getPotencia());
        aparelhoRequest.setTipo(aparelho.getTipo());

        return aparelhoRequest;
    }

}
