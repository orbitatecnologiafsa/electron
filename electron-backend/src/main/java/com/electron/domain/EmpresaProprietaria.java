package com.electron.domain;

import java.time.LocalDate;

import com.electron.domain.enums.RegimeTributario;
import com.electron.domain.enums.TipoEmpresa;
import com.electron.domain.enums.VersaoEmpresa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresa_proprietaria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaProprietaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresa_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "empresatipo", nullable = false)
    private TipoEmpresa tipo;

    @Column(name = "empresa_cpf_cnpj", nullable = false, unique = true, length = 18)
    private String cpfCnpj;

    @Enumerated(EnumType.STRING)
    @Column(name = "empresa_versao")
    private VersaoEmpresa versao;

    @Enumerated(EnumType.STRING)
    @Column(name = "empresa_regime_tributario", nullable = false)
    private RegimeTributario regimeTributario;

    @Column(name = "empresa_cnae", length = 10)
    private String cnae;

    @Column(name = "empresa_razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "empresa_numero_filial", length = 4)
    private String numeroFilial;

    @Column(name = "empresa_dv", length = 2)
    private String digitoVerificador;

    @Column(name = "empresa_ativo")
    private Boolean ativo = true;

    @Column(name = "empresa_nome_fantasia")
    private String nomeFantasia;

    @Column(name = "empresa_nome_exibicao")
    private String nomeExibicao;

    @Column(name = "empresa_natureza_juridica")
    private String naturezaJuridica;

    @Column(name = "empresa_inscricao_estadual", nullable = false, length = 25)
    private String inscricaoEstadual;

    @Column(name = "empresa_inscricao_municipal", length = 25)
    private String inscricaoMunicipal;

    @Column(name = "empresa_contato", length = 60)
    private String contato;

    @Column(name = "empresa_telefone", length = 15)
    private String telefone;

    @Column(name = "empresa_email", unique = true)
    private String email;

    @Column(name = "empresa_cep", nullable = false, length = 10)
    private String cep;

    @Column(name = "empresa_logradouro", nullable = false)
    private String logradouro;

    @Column(name = "empresa_numero", nullable = false, length = 10)
    private String numero;

    @Column(name = "empresa_bairro", nullable = false, length = 100)
    private String bairro;

    @Column(name = "empresa_complemento", nullable = false, length = 100)
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "empresa_fk_municipios", nullable = false)
    private Municipio municipio;

    @Column(name = "empresa_chave_acesso", unique = true)
    private String chaveAcesso;

    @Column(name = "empresa_data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "empresa_descricao_atividades", columnDefinition = "TEXT")
    private String descricaoAtividades;

    @Column(name = "empresa_observacoes", columnDefinition = "TEXT")
    private String observacoes;

}
