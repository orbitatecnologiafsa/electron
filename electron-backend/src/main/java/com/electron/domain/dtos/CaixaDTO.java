package com.electron.domain.dtos;

import com.electron.domain.enums.TipoCaixa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaixaDTO {
    private Long id;

    @NotNull(message = "Tipo de caixa é obrigatório")
    private TipoCaixa tipo;

    @NotNull(message = "Número é obrigatório")
    @Positive(message = "Número deve ser positivo")
    private Integer numero;

    @NotBlank(message = "Modelo é obrigatório")
    @Size(max = 100, message = "Modelo não pode ter mais que 100 caracteres")
    private String modelo;

    @NotBlank(message = "Série é obrigatória")
    @Size(max = 50, message = "Série não pode ter mais que 50 caracteres")
    private String serie;

    private Boolean ativo = true;

    private LocalDate dataInstalacao;

    @Size(max = 1000, message = "Observações não podem ter mais que 1000 caracteres")
    private String observacoes;

    @NotNull(message = "Empresa proprietária é obrigatória")
    private Long empresaProprietariaId;
}
