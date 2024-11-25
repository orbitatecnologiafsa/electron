package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tributacao_estadual")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TributacaoEstadual {

    @Column(name = "tributacao_estadual_contribuinte")
    private final Boolean contribuinte = false;
    @Column(name = "tributacao_estadual_nao_contribuinte")
    private final Boolean naoContribuinte = false;
    @Column(name = "tributacao_estadual_isento")
    private final Boolean isento = false;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributacao_estadual_id")
    private Long id;
    @Column(name = "tributacao_estadual_nome", nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "tributacao_estadual_fk_UF_origem", insertable = false, updatable = false)
    private Estado origem;

    @ManyToOne
    @JoinColumn(name = "tributacao_estadual_fk_UF_origem", insertable = false, updatable = false)
    private Estado destino;

    @Column(name = "tributacao_estadual_percentual_basecalculo_icms", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculoIcms;

    @Column(name = "tributacao_estadual_percentual_basecalculo_icms_st", precision = 10, scale = 2)
    private BigDecimal percentualBaseCalculoIcmsSt;

    @Column(name = "tributacao_estadual_percentual_mva_pauta", precision = 10, scale = 2)
    private BigDecimal percentualMvaPauta;

    @ManyToOne
    @JoinColumn(name = "tributacao_estadual_fk_cfop")
    private TributoCfop cfop;

    @Column(name = "tributacao_estadual_percentual_icms", precision = 10, scale = 2)
    private BigDecimal percentualIcms;

    @Column(name = "tributacao_estadual_percentual_icms_aplicado_destinatario", precision = 10, scale = 2)
    private BigDecimal percentualIcmsAplicadoDestinatario;

    @Column(name = "tributacao_estadual_percentual_diferimento", precision = 10, scale = 2)
    private BigDecimal percentualDiferimento;

    @ManyToOne
    @JoinColumn(name = "tributacao_estadual_fk_cst")
    private TributoCst cst;

    @ManyToOne
    @JoinColumn(name = "tributacao_estadual_fk_csosn")
    private TributoCsosn csosn;

    @ManyToOne
    @JoinColumn(name = "tributacao_estadual_fk_modalidadecalculo_icms_ST")
    private ModalidadeDeCalculoIcmsSt modalidadeCalculoIcmsSt;

    @Column(name = "tributacao_estadual_percentual_fcpobreza", precision = 10, scale = 2)
    private BigDecimal percentualFcpobreza;

    @Column(name = "tributacao_estadual_observacoes", columnDefinition = "TEXT")
    private String observacoes;

    public TributacaoEstadual(Long id) {
        this.id = id;
    }

}