package com.electron.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registros")
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Registro {
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
