package com.api.gs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Aparelho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDAPARELHO")
    private Long idAparelho;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "potencia", nullable = false)
    private Long potencia;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @OneToMany(mappedBy = "aparelho", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consumo> consumos;
}
