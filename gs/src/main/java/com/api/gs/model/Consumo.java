package com.api.gs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idconsumo")
    private Long idConsumo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aparelho_id", referencedColumnName = "idaparelho", foreignKey = @ForeignKey(name = "FK_CONSUMO_APARELHO"))
    private Aparelho aparelho;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "consumo_kwh", nullable = false)
    private Double consumoKwh;

    @Column(name = "custo_estimado", nullable = false)
    private Double custoEstimado;
}
