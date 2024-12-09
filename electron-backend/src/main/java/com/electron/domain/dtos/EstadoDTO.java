package com.electron.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome não pode ter mais que 100 caracteres")
    private String nome;

    @NotBlank(message = "Código IBGE é obrigatório")
    private int codigoIBGE;

    @NotBlank(message = "UF é obrigatório")
    @Size(max = 2, message = "UF não pode ter mais que 2 caracteres")
    private String uf;
}
