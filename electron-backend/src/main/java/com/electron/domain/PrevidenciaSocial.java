package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "previdencia_social")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrevidenciaSocial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "retencao_contribuicao_previdenciaria", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private final Boolean retencaoContribuicaoPrevidenciaria = false;

    @Column(name = "previdencia_social_percentual_basecalculo")
    private BigDecimal percentualBaseCalculo;

    @Column(name = "previdencia_social_percentual")
    private BigDecimal percentual;

    @Column(name = "previdencia_social_observacoes")
    private String observacoes;
}
