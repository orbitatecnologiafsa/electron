package com.electron.domain;

import com.electron.domain.enums.TipoMovimentacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "cst_cofins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CstCofins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cst_cofins_id")
    private Long id;

    @Column(name = "cst_cofins_codigo", length = 10, nullable = false)
    private String codigo;

    @Column(name = "cst_cofins_descricao", columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "cst_cofins_tipo_movimentacao", nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    @Column(name = "cst_cofins_tipo_tributacao", length = 50)
    private String tributacao;

    @Column(name = "cst_cofins_aliquota", precision = 10, scale = 4)
    private BigDecimal aliquota;

    @Column(name = "cst_cofins_incidencia", length = 50)
    private String incidencia;

    @Column(name = "cst_cofins_natureza_receita", length = 100)
    private String naturezaReceita;

    @Column(name = "cst_cofins_ativo")
    private Boolean ativo = true;

    @Column(name = "cst_cofins_observacoes", columnDefinition = "TEXT")
    private String observacoes;
}

