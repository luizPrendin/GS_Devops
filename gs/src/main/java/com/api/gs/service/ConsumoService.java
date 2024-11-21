package com.api.gs.service;

import com.api.gs.DTO.ConsumoRequest;
import com.api.gs.DTO.ConsumoResponse;
import com.api.gs.model.Consumo;
import org.springframework.stereotype.Service;

@Service
public class ConsumoService {

    public Consumo requestToConsumo(ConsumoRequest consumoRequest) {
        Consumo consumo = new Consumo();
        consumo.setAparelho(consumoRequest.getAparelho());
        consumo.setData(consumoRequest.getData());
        consumo.setConsumoKwh(consumoRequest.getConsumoKwh());
        consumo.setCustoEstimado(consumoRequest.getCustoEstimado());
        return consumo;
    }

    public ConsumoResponse consumoToResponse(Consumo consumo) {
        ConsumoResponse consumoResponse = new ConsumoResponse();
        consumoResponse.setIdConsumo(consumo.getIdConsumo());
        consumoResponse.setAparelho(consumo.getAparelho());
        consumoResponse.setData(consumo.getData());
        consumoResponse.setConsumoKwh(consumo.getConsumoKwh());
        consumoResponse.setCustoEstimado(consumo.getCustoEstimado());
        return consumoResponse;
    }

    public ConsumoRequest consumoToRequest(Consumo consumo) {
        ConsumoRequest consumoRequest = new ConsumoRequest();
        consumoRequest.setAparelho(consumo.getAparelho());
        consumoRequest.setData(consumo.getData());
        consumoRequest.setConsumoKwh(consumo.getConsumoKwh());
        consumoRequest.setCustoEstimado(consumo.getCustoEstimado());
        return consumoRequest;
    }
}
