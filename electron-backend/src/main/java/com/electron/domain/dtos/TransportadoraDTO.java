package com.electron.domain.dtos;

import com.electron.domain.enums.TipoPessoa;
import com.electron.validation.ValidCep;
import com.electron.validation.ValidCpfCnpj;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportadoraDTO {

    private Long id;

    @NotNull(message = "Tipo de pessoa é obrigatório")
    private TipoPessoa tipo;

    private String foto;

    @NotBlank(message = "Entidade é obrigatória")
    private String entidade;

    @NotBlank(message = "CPF/CNPJ é obrigatório")
    @ValidCpfCnpj(message = "CPF/CNPJ inválido")
    private String cpfCnpj;

    @NotBlank(message = "Nome/Razão Social é obrigatório")
    @Size(min = 3, max = 100, message = "Nome/Razão Social deve ter entre 3 e 100 caracteres")
    private String nomeRazaoSocial;

    @Size(max = 100, message = "Nome fantasia não pode ter mais que 100 caracteres")
    private String nomeFantasia;

    private String passaporte;

    @Size(max = 20, message = "RG/Inscrição Estadual não pode ter mais que 20 caracteres")
    private String rgInscricaoEstadual;

    @Size(max = 20, message = "Inscrição Municipal não pode ter mais que 20 caracteres")
    private String inscricaoMunicipal;

    @Size(max = 60, message = "Contato não pode ter mais que 60 caracteres")
    private String contato;

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

    @NotNull(message = "Empresa proprietária é obrigatório")
    private Long empresaId;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4}-\\d{4}|\\(\\d{2}\\) \\d{5}-\\d{4}",
            message = "Telefone deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
    private String telefone;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4}-\\d{4}|\\(\\d{2}\\) \\d{5}-\\d{4}",
            message = "Celular deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
    private String celular;

    @Email(message = "E-mail inválido")
    private String email;

    private LocalDate dataDeNascimento;

    @Size(max = 1000, message = "Observações não podem ter mais que 1000 caracteres")
    private String observacoes;

    @Size(max = 10, message = "Placa do veículo não pode ter mais que 10 caracteres")
    private String placaVeiculo;

    @Size(max = 50, message = "ANTT do veículo não pode ter mais que 50 caracteres")
    private String anttVeiculo;

}
