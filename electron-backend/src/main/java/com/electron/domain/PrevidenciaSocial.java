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
    @Column(name = "previdencia_social_id")
    private Long id;

    @Column(name = "previdencia_social_percentual_basecalculo", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculo;

    @Column(name = "previdencia_social_percentual", precision = 10, scale = 2)
    private BigDecimal percentual;

    @Column(name = "previdencia_social_observacoes", columnDefinition = "TEXT")
    private String observacoes;
}
