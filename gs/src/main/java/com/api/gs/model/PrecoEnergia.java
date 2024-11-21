package com.api.gs.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRECOENERGIA")
public class PrecoEnergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPRECOENERGIA")
    private Long idPrecoEnergia;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "preco_kwh", nullable = false)
    private Double precoKwh;
}
