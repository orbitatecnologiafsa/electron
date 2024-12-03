package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tributo_csosn")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TributoCsosn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributo_csosn_id")
    private Long id;
    @Column(name = "tributo_csosn_codigo", nullable = false, unique = true, length = 3)
    private String codigo;
    @Column(name = "tributo_csosn_descricao", columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(name = "tributo_csosn_aliquota", precision = 5, scale = 2)
    private BigDecimal aliquota;

    @Column(name = "tributo_csosn_ativo")
    private final Boolean ativo = true;
}