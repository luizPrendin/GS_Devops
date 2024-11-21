package com.api.gs.service;

import com.api.gs.DTO.PrecoEnergiaRequest;
import com.api.gs.DTO.PrecoEnergiaResponse;
import com.api.gs.model.PrecoEnergia;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public class PrecoEnergiaService {

    public PrecoEnergia requestToPrecoEnergia(PrecoEnergiaRequest precoEnergiaRequest) {
        PrecoEnergia precoEnergia = new PrecoEnergia();
        precoEnergia.setData(precoEnergiaRequest.getData());
        precoEnergia.setPrecoKwh(precoEnergiaRequest.getPrecoKwh());
        return precoEnergia;
    }

    public PrecoEnergiaResponse precoEnergiaToResponse(PrecoEnergia precoEnergia) {
        PrecoEnergiaResponse precoEnergiaResponse = new PrecoEnergiaResponse();
        precoEnergiaResponse.setIdPrecoEnergia(precoEnergia.getIdPrecoEnergia());
        precoEnergiaResponse.setData(precoEnergia.getData());
        precoEnergiaResponse.setPrecoKwh(precoEnergia.getPrecoKwh());
        return precoEnergiaResponse;
    }

    public PrecoEnergiaRequest precoEnergiaToRequest(PrecoEnergia precoEnergia) {
        PrecoEnergiaRequest precoEnergiaRequest = new PrecoEnergiaRequest();
        precoEnergiaRequest.setData(precoEnergia.getData());
        precoEnergiaRequest.setPrecoKwh(precoEnergia.getPrecoKwh());
        return precoEnergiaRequest;
    }
}
