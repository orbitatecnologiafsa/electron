package com.electron.domain;

import com.electron.domain.enums.RegimeTributario;
import com.electron.domain.enums.TipoPessoa;
import com.electron.domain.enums.TipoUnidade;
import com.electron.domain.enums.VersaoEmpresa;
import com.electron.validation.ValidCpfCnpj;
import com.electron.validation.ValidCep;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "empresa_proprietaria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaProprietaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresa_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "empresa_tipo_unidade", nullable = false)
    private TipoUnidade tipoUnidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "empresa_tipo_pessoa", nullable = false)
    private TipoPessoa tipoPessoa;

    @ValidCpfCnpj
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

    @Pattern(regexp = "^\\(?\\d{2}\\)? ?\\d{4,5}-?\\d{4}$", message = "Telefone deve ser no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
    @Column(name = "empresa_telefone", length = 15)
    private String telefone;

    @Email(message = "Email inválido")
    @Column(name = "empresa_email", unique = true)
    private String email;

    @ValidCep
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
   // @JsonIgnore
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
