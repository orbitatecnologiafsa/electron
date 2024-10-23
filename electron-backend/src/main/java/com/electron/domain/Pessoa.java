package com.electron.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cpfCnpj;

    @Column(nullable = false)
    private String nomeRazao;

    private String fantasia;

    private String rgInscricaoEstadual;

    private String inscricaoMunicipal;

    private String contato;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String bairro;

    private String complemento;

    @Column(nullable = false)
    private String uf;

    @Column(nullable = false)
    private String municipio;

    private String telefone;

    private String celular;

    @Column(unique = true)
    private String email;

    private String observacao;

    private Boolean ativo = true;

    // Adicionando o tipo de pessoa
    @Column(nullable = false)
    private String tipo; // 'Pessoa Física', 'Pessoa Jurídica', 'Estrangeiro'
}