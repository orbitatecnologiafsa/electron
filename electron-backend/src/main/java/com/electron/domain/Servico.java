package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String descricao;
    private String undSaida;
    private String grupo;
    private String atividade;
    private Float custo = 0.00f;
    private Float precoVenda = 0.00f;
    private Float precoRevenda = 0.00f;
    private String tributacaoMunicipal;
    private String tributacaoFederal;
    private String observacao;
    private Boolean ativo;
}
