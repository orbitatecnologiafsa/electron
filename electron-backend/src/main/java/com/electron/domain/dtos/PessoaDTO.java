package com.electron.domain.dtos;

import com.electron.domain.enums.TipoPessoa;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private Long id;

    @Enumerated(EnumType.STRING)
//    @NotBlank(message = "Tipo de pessoa é obrigatório")
    private TipoPessoa tipo;

    private String foto;

    @NotBlank(message = "Entidade é obrigatória")
    @Size(max = 60, message = "Entidade não pode ter mais que 60 caracteres")
    private String entidade;

    @NotBlank(message = "CPF/CNPJ é obrigatório")
    private String cpfCnpj;

    @NotBlank(message = "Nome/Razão Social é obrigatório")
    private String nomeRazaoSocial;

    private String nomeFantasia;

    private String passaporte;

    private String rgInscricaoEstadual;

    private String inscricaoMunicipal;

    private String contato;

    @NotBlank(message = "CEP é obrigatório")
    private String cep;

    @NotBlank(message = "Logradouro é obrigatório")
    private String logradouro;

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

    private String complemento;

    private Long municipioId;

    @Pattern(regexp = "^\\(?\\d{2}\\)? ?\\d{4,5}-?\\d{4}$", message = "Telefone deve ser no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
    private String telefone;

    private String celular;

    @Email(message = "Email inválido")
    private String email;

    private LocalDate dataDeNascimento;

    private String observacoes;

    private Long empresaId;
}
