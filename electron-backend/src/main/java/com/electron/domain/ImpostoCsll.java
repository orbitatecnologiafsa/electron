package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "imposto_csll")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImpostoCsll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "percentual_basecalculo_csll", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculoCsll;

    @Column(name = "percentual_csll", precision = 10, scale = 2)
    private BigDecimal percentualCsll;

    @Column(name = "imposto_retencao_csll")
    private Boolean impostoRetencaoCsll = false;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}
