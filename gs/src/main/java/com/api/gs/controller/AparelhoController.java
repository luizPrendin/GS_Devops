package com.api.gs.controller;

import com.api.gs.DTO.AparelhoRequest;
import com.api.gs.DTO.AparelhoResponse;
import com.api.gs.model.Aparelho;
import com.api.gs.repository.AparelhoRepository;
import com.api.gs.service.AparelhoService;
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
@RequestMapping(value = "aparelho")
public class AparelhoController {

    @Autowired
    private AparelhoRepository aparelhoRepository;

    @Autowired
    private AparelhoService aparelhoService;

    @PostMapping
    public ResponseEntity<AparelhoResponse> create(@Valid @RequestBody AparelhoRequest aparelhoRequest) {
        Aparelho aparelho = aparelhoService.requestToAparelho(aparelhoRequest);
        Aparelho aparelhoPersistido = aparelhoRepository.save(aparelho);
        AparelhoResponse aparelhoResponse = aparelhoService.aparelhoToResponse(aparelhoPersistido);
        return new ResponseEntity<>(aparelhoResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AparelhoResponse>> read() {
        List<Aparelho> aparelhos = aparelhoRepository.findAll();
        if (aparelhos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<AparelhoResponse> aparelhoResponses = new ArrayList<>();
        for (Aparelho aparelho : aparelhos) {
            AparelhoResponse aparelhoResponse = aparelhoService.aparelhoToResponse(aparelho);
            aparelhoResponse.setLink(linkTo(methodOn(AparelhoController.class).read()).withSelfRel());
            aparelhoResponses.add(aparelhoResponse);
        }
        return new ResponseEntity<>(aparelhoResponses, HttpStatus.OK);
    }

    @GetMapping("/{idAparelho}")
    public ResponseEntity<AparelhoResponse> read(@PathVariable Long idAparelho) {
        Optional<Aparelho> aparelho = aparelhoRepository.findById(idAparelho);
        if (aparelho.isPresent()) {
            AparelhoResponse aparelhoResponse = aparelhoService.aparelhoToResponse(aparelho.get());
            aparelhoResponse.setLink(linkTo(methodOn(AparelhoController.class).read()).withRel("Lista de aparelhos"));
            return new ResponseEntity<>(aparelhoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{idAparelho}")
    public ResponseEntity<AparelhoResponse> update(@PathVariable Long idAparelho, @Valid @RequestBody AparelhoRequest aparelhoRequest) {
        Optional<Aparelho> aparelhoPersistido = aparelhoRepository.findById(idAparelho);
        if (aparelhoPersistido.isPresent()) {
            Aparelho aparelho = aparelhoService.requestToAparelho(aparelhoRequest);
            aparelho.setIdAparelho(idAparelho);
            Aparelho aparelhoAtualizado = aparelhoRepository.save(aparelho);
            AparelhoResponse aparelhoResponse = aparelhoService.aparelhoToResponse(aparelhoAtualizado);
            return new ResponseEntity<>(aparelhoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{idAparelho}")
    public ResponseEntity<Void> delete(@PathVariable Long idAparelho) {
        Optional<Aparelho> aparelho = aparelhoRepository.findById(idAparelho);
        if (aparelho.isPresent()) {
            aparelhoRepository.delete(aparelho.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
