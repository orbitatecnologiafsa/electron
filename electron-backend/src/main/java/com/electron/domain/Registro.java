package com.electron.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "registros")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @OneToMany(mappedBy = "registro")
    private List<Cliente> clientes;

    @OneToMany(mappedBy = "registro")
    private List<Fornecedor> fornecedores;

    @OneToMany(mappedBy = "registro")
    private List<Transportador> transportadoras;

    @OneToMany(mappedBy = "registro")
    private List<Vendedor> vendedores;

}
