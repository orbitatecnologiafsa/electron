package com.electron.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpfCnpj;
    private String nomeRazao;
    private String fantasia;
    private String rgInscricaoEstadual;
    private String inscricaoEstadualMunicipal;
    private String contato;
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String uf;
    private String municipio;
    private String telefone;
    private String celular;

    @Column(unique = true)
    private String email;

    private String observacao;
    private Boolean ativo;
}
