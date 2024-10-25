package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "imposto_ir")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImpostoIr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "percentual_basecalculo_ir", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculoIr;

    @Column(name = "percentual_ir", precision = 10, scale = 2)
    private BigDecimal percentualIr;

    @Column(name = "imposto_retencao_ir")
    private Boolean impostoRetencaoIr = false;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}