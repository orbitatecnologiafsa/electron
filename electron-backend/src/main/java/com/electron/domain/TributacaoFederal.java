package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tributacao_federal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TributacaoFederal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributacao_federal_id")
    private Long id;

    @Column(name = "tributacao_federal_nome")
    private String nome;

    @Column(name = "tributacao_federal_descricao", columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "tributacao_federal_fk_tributo_ipi")
    private TributoIpi tributoIpi;

    @ManyToOne
    @JoinColumn(name = "tributacao_federal_fk_tributo_pis")
    private TributoPis tributoPis;

    @ManyToOne
    @JoinColumn(name = "tributacao_federal_fk_tributo_cofins")
    private TributoConfins tributoConfins;

    @ManyToOne
    @JoinColumn(name = "tributacao_federal_fk_tributo_ir")
    private TributoIr tributoIr;

    @ManyToOne
    @JoinColumn(name = "tributacao_federal_fk_tributo_csll")
    private TributoCsll tributoCsll;

    @ManyToOne
    @JoinColumn(name = "tributacao_federal_fk_previdencia_social")
    private PrevidenciaSocial previdenciaSocial;

    @Column(name = "tributacao_federal_observacoes", columnDefinition = "TEXT")
    private String observacoes;
}
