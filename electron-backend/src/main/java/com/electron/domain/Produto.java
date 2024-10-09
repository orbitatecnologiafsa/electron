package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer barras;
    private String nome;
    private String descricao;
    private String undSaida;
    private String undEntrada;
    private String undEstocagem;
    private String undTrib;
    private String fatoConversao;
    private String grupo;
    private String colecao;
    private String classificacao;
    private Integer diasValidade;
    private Boolean pesavel;
    private Boolean vendeFracionado;
    private Boolean composicao;
    private Boolean controlaGrade;
    private Float custo = 0.00f;
    private Float custoMedio = 0.00f;
    private Float margemLucro = 0.00f;
    private Float precoVenda = 0.00f;
    private Float precoRevenda = 0.00f;
    private Float saldo = 0.0000f;
    private Float bloqueado = 0.0000f;
    private Float pedidoVenda = 0.0000f;
    private Float disponivel = 0.0000f;
    private Float saldoIdeal = 0.0000f;
    private String codigoAjuste;
    private String NCM;
    private String CEST;
    private String tributacaoEstadual;
    private String tributacaoFederal;
    private String observacao;
    private String informacaoExtra;
    private String undEmbalagem;
    private Float qntEmbalagem = 0.0000f;
    private String codigoANP;
    private String referencia;
    private String localizacao;
    private Boolean ativo;

}
