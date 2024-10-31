package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "servico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servico_id")
    private Integer servicoId;

    @Column(name = "servico_codigo", nullable = false, length = 20)
    private String servicoCodigo;

    @Column(name = "servico_nome", nullable = false, length = 255)
    private String servicoNome;

    @Column(name = "servico_descricao")
    private String servicoDescricao;

    @Column(name = "servico_unidade_saida")
    private Integer servicoUnidadeSaida;

    @ManyToOne
    @JoinColumn(name = "servico_fk_grupo_prod_serv")
    private GrupoProdServ grupoProdServ;

    @ManyToOne
    @JoinColumn(name = "servico_fk_subgrupo_prod_serv")
    private SubgrupoProdServ subgrupoProdServ;

    @ManyToOne
    @JoinColumn(name = "servico_fk_atividade")
    private ServicoAtividade servicoAtividade;

    @Column(name = "servico_custo")
    private BigDecimal servicoCusto;

    @Column(name = "servico_preco_venda")
    private BigDecimal servicoPrecoVenda;

    @Column(name = "servico_preco_revenda")
    private BigDecimal servicoPrecoRevenda;

    @ManyToOne
    @JoinColumn(name = "servico_fk_tributacao_municipal")
    private TributacaoMunicipal tributacaoMunicipal;

    @ManyToOne
    @JoinColumn(name = "servico_fk_tributacao_federal")
    private TributacaoFederal tributacaoFederal;

    @Column(name = "observacoes")
    private String observacoes;
}