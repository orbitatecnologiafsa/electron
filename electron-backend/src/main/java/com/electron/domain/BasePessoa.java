package com.electron.domain;

import com.electron.domain.enums.TipoPessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BasePessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pessoas_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "pessoas_tipo", nullable = false)
    private TipoPessoa tipo;

    @Column(name = "pessoas_foto")
    private String foto;

    @Column(name = "pessoas_entidade", nullable = false, length = 60)
    private String entidade;

    @Column(name = "pessoas_cpf_cnpj", nullable = false, unique = true, length = 18)
    private String cpfCnpj;

    @Column(name = "pessoas_nome_razao_social", nullable = false)
    private String nomeRazaoSocial;

    @Column(name = "pessoas_nome_fantasia")
    private String nomeFantasia;

    @Column(name = "pessoas_passaporte", length = 60)
    private String passaporte;

    @Column(name = "pessoas_rg_inscricao_estadual", length = 25)
    private String rgInscricaoEstadual;

    @Column(name = "pessoas_inscricao_municipal", length = 25)
    private String inscricaoMunicipal;

    @Column(name = "pessoas_contato")
    private String contato;

    @Column(name = "pessoas_cep", nullable = false, length = 10)
    private String cep;

    @Column(name = "pessoas_logradouro", nullable = false)
    private String logradouro;

    @Column(name = "pessoas_numero", nullable = false, length = 10)
    private String numero;

    @Column(name = "pessoas_bairro", nullable = false, length = 100)
    private String bairro;

    @Column(name = "pessoas_complemento", length = 100)
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "pessoas_fk_municipios", nullable = false)
    private Municipio municipio;

    @Column(name = "pessoas_telefone", length = 15)
    private String telefone;

    @Column(name = "pessoas_celular", length = 15)
    private String celular;

    @Column(name = "pessoas_email", unique = true)
    private String email;

    @Column(name = "pessoas_data_de_nascimento")
    private LocalDate dataDeNascimento;

    @Column(name = "pessoas_observacoes")
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "pessoas_fk_empresa")
    private EmpresaProprietaria empresa;

}