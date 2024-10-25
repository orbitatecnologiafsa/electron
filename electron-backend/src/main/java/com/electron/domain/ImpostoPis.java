package com.electron.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "imposto_pis")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImpostoPis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(max = 10)
    private String codigo;

    private String nome;

    private String naturezaDaReceita;

    @Column(precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculo;

    @Column(precision = 10, scale = 2)
    private BigDecimal percentualPis;

    @Column(name = "percentual_basecalculo_pis_st", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculoPisSt;

    @Column(name = "percentual_pis_st", precision = 10, scale = 2)
    private BigDecimal percentualPisSt;

    @Column(name = "percentual_bc_pis_retido", precision = 10, scale = 2)
    private BigDecimal percentualBcPisRetido;

    @Column(name = "imposto_retencao_pis")
    private Boolean impostoRetencaoPis = false;

    @Column(name = "imposto_pis_retido", precision = 10, scale = 2)
    private BigDecimal impostoPisRetido;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

}
