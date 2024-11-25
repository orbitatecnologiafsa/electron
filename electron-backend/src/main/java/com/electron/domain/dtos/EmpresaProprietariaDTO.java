package com.electron.domain.dtos;

import com.electron.domain.enums.RegimeTributario;
import com.electron.domain.enums.TipoPessoa;
import com.electron.domain.enums.TipoUnidade;
import com.electron.domain.enums.VersaoEmpresa;
import com.electron.validation.ValidCep;
import com.electron.validation.ValidCpfCnpj;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaProprietariaDTO {
    private Long id;

    @NotNull(message = "Tipo de unidade é obrigatório")
    private TipoUnidade tipoUnidade;

    @NotNull(message = "Tipo de pessoa é obrigatório")
    private TipoPessoa tipoPessoa;

    @NotBlank(message = "CPF/CNPJ é obrigatório")
    @ValidCpfCnpj(message = "CPF/CNPJ inválido")
    private String cpfCnpj;

    @NotNull(message = "Versão é obrigatória")
    private VersaoEmpresa versao;

    @NotNull(message = "Regime tributário é obrigatório")
    private RegimeTributario regimeTributario;

    @NotBlank(message = "CNAE é obrigatório")
    @Size(max = 20, message = "CNAE não pode ter mais que 20 caracteres")
    private String cnae;

    @NotBlank(message = "Razão social é obrigatória")
    @Size(max = 100, message = "Razão social não pode ter mais que 100 caracteres")
    private String razaoSocial;

    @Size(max = 10, message = "Número filial não pode ter mais que 10 caracteres")
    private String numeroFilial;

    @Size(max = 2, message = "Dígito verificador não pode ter mais que 2 caracteres")
    private String digitoVerificador;

    private Boolean ativo = true;

    @Size(max = 100, message = "Nome fantasia não pode ter mais que 100 caracteres")
    private String nomeFantasia;

    @Size(max = 100, message = "Nome exibição não pode ter mais que 100 caracteres")
    private String nomeExibicao;

    @Size(max = 50, message = "Natureza jurídica não pode ter mais que 50 caracteres")
    private String naturezaJuridica;

    @Size(max = 20, message = "Inscrição estadual não pode ter mais que 20 caracteres")
    private String inscricaoEstadual;

    @Size(max = 20, message = "Inscrição municipal não pode ter mais que 20 caracteres")
    private String inscricaoMunicipal;

    @Size(max = 60, message = "Contato não pode ter mais que 60 caracteres")
    private String contato;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4}-\\d{4}|\\(\\d{2}\\) \\d{5}-\\d{4}",
            message = "Telefone deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
    private String telefone;

    @Email(message = "E-mail inválido")
    private String email;

    @ValidCep(message = "CEP inválido")
    private String cep;

    @NotBlank(message = "Logradouro é obrigatório")
    @Size(max = 255, message = "Logradouro não pode ter mais que 255 caracteres")
    private String logradouro;

    @NotBlank(message = "Número é obrigatório")
    @Size(max = 10, message = "Número não pode ter mais que 10 caracteres")
    private String numero;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 100, message = "Bairro não pode ter mais que 100 caracteres")
    private String bairro;

    @Size(max = 100, message = "Complemento não pode ter mais que 100 caracteres")
    private String complemento;

    @NotNull(message = "Município é obrigatório")
    private Long municipioId;

    @Size(max = 100, message = "Chave de acesso não pode ter mais que 100 caracteres")
    private String chaveAcesso;

    private LocalDate dataCriacao;

    @Size(max = 1000, message = "Descrição de atividades não pode ter mais que 1000 caracteres")
    private String descricaoAtividades;

    @Size(max = 1000, message = "Observações não podem ter mais que 1000 caracteres")
    private String observacoes;
}
