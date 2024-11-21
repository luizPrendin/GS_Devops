package com.api.gs.controller;

import com.api.gs.DTO.ConsumoRequest;
import com.api.gs.DTO.ConsumoResponse;
import com.api.gs.model.Consumo;
import com.api.gs.repository.ConsumoRepository;
import com.api.gs.service.ConsumoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "consumo")
public class ConsumoController {

    @Autowired
    private ConsumoRepository consumoRepository;

    @Autowired
    private ConsumoService consumoService;

    @PostMapping
    public ResponseEntity<ConsumoResponse> create(@Valid @RequestBody ConsumoRequest consumoRequest) {
        Consumo consumo = consumoService.requestToConsumo(consumoRequest);
        Consumo consumoPersistido = consumoRepository.save(consumo);
        ConsumoResponse consumoResponse = consumoService.consumoToResponse(consumoPersistido);
        return new ResponseEntity<>(consumoResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConsumoResponse>> read() {
        List<Consumo> consumos = consumoRepository.findAll();
        if (consumos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<ConsumoResponse> consumoResponses = new ArrayList<>();
        for (Consumo consumo : consumos) {
            ConsumoResponse consumoResponse = consumoService.consumoToResponse(consumo);
            consumoResponse.setLink(linkTo(methodOn(ConsumoController.class).read()).withSelfRel());
            consumoResponses.add(consumoResponse);
        }
        return new ResponseEntity<>(consumoResponses, HttpStatus.OK);
    }

    @GetMapping("/{idConsumo}")
    public ResponseEntity<ConsumoResponse> read(@PathVariable Long idConsumo) {
        Optional<Consumo> consumo = consumoRepository.findById(idConsumo);
        if (consumo.isPresent()) {
            ConsumoResponse consumoResponse = consumoService.consumoToResponse(consumo.get());
            consumoResponse.setLink(linkTo(methodOn(ConsumoController.class).read()).withRel("Lista de consumos"));
            return new ResponseEntity<>(consumoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{idConsumo}")
    public ResponseEntity<ConsumoResponse> update(@PathVariable Long idConsumo, @Valid @RequestBody ConsumoRequest consumoRequest) {
        Optional<Consumo> consumoPersistido = consumoRepository.findById(idConsumo);
        if (consumoPersistido.isPresent()) {
            Consumo consumo = consumoService.requestToConsumo(consumoRequest);
            consumo.setIdConsumo(idConsumo);
            Consumo consumoAtualizado = consumoRepository.save(consumo);
            ConsumoResponse consumoResponse = consumoService.consumoToResponse(consumoAtualizado);
            return new ResponseEntity<>(consumoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{idConsumo}")
    public ResponseEntity<Void> delete(@PathVariable Long idConsumo) {
        Optional<Consumo> consumo = consumoRepository.findById(idConsumo);
        if (consumo.isPresent()) {
            consumoRepository.delete(consumo.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
