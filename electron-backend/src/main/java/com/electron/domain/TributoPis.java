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
public class TributoPis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributo_pis_id")
    private Long id;

    @Column
    @Size(max = 10)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "tributo_pis_fk_cst_pis")
    private CstPis cstPis;

    @Column(name = "tributo_pis_natureza_da_receita", length = 100)
    private String naturezaDaReceita;

    @Column(name = "tributo_pis_percentual_basecalculo_pis", precision = 10, scale = 4)
    private BigDecimal percentualBaseCalculoPis;

    @Column(name = "tributo_pis_percentual_pis", precision = 10, scale = 4)
    private BigDecimal percentualPis;

    @Column(name = "tributo_pis_percentual_basecalculo_pis_st", precision = 10, scale = 4)
    private BigDecimal percentualBaseCalculoPisSt;

    @Column(name = "tributo_pis_percentual_pis_st", precision = 10, scale = 4)
    private BigDecimal percentualPisSt;

    @Column(name = "tributo_pis_percentual_bc_pis_retido", precision = 10, scale = 4)
    private BigDecimal percentualBcPisRetido;

    @Column(name = "tributo_pis_retido", precision = 10, scale = 4)
    private BigDecimal retido;

    @Column(name = "tributo_retencao_pis")
    private Boolean retencaoPis = false;

    @Column(name = "tributo_pis_observacoes", columnDefinition = "TEXT")
    private String observacoes;

}
