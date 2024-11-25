package com.electron.domain.dtos;

import com.electron.domain.enums.EstadoCivil;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutrasInformacoesDTO {
    private Long id;

    @NotNull(message = "Pessoa é obrigatória")
    private Long pessoaId;

    private EstadoCivil estadoCivil;

    @Size(max = 100, message = "Nome do cônjuge não pode ter mais que 100 caracteres")
    private String conjuge;

    @Size(max = 100, message = "Nome da mãe não pode ter mais que 100 caracteres")
    private String nomeMae;

    @Size(max = 100, message = "Nome do pai não pode ter mais que 100 caracteres")
    private String nomePai;

    @Size(max = 100, message = "Local de trabalho não pode ter mais que 100 caracteres")
    private String localTrabalho;

    @Size(max = 100, message = "Outras profissões não pode ter mais que 100 caracteres")
    private String outrasProfissao;

    private LocalDate dataNascimento;

    @Size(max = 100, message = "Naturalidade não pode ter mais que 100 caracteres")
    private String naturalidade;

    @Min(value = 1, message = "Dia de acerto deve ser entre 1 e 31")
    @Max(value = 31, message = "Dia de acerto deve ser entre 1 e 31")
    private Integer diaAcerto;

    @DecimalMin(value = "0.0", message = "Renda mensal não pode ser negativa")
    @Digits(integer = 10, fraction = 2, message = "Renda mensal deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal rendaMensal;

    @DecimalMin(value = "0.0", message = "Limite de crédito não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Limite de crédito deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal limiteCredito;

    @DecimalMin(value = "0.0", message = "Crédito disponível não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Crédito disponível deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal creditoDisponivel;

    private LocalDate creditoData;

    @Min(value = 1, message = "Dia de faturamento deve ser entre 1 e 31")
    @Max(value = 31, message = "Dia de faturamento deve ser entre 1 e 31")
    private Integer diaFaturamento;

    @Size(max = 50, message = "Forma de pagamento não pode ter mais que 50 caracteres")
    private String formaPagamento;

    @Min(value = 1, message = "Número de parcelas deve ser maior que zero")
    private Integer numParcelasFaturamento;

    private Boolean reterIr = false;
    private Boolean reterCsll = false;
    private Boolean reterPrevidenciaSocial = false;
    private Boolean reterCofins = false;
    private Boolean reterPis = false;
}
