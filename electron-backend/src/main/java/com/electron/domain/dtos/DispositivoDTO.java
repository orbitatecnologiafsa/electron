package com.electron.domain.dtos;

import com.electron.domain.enums.TipoDispositivo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DispositivoDTO {
    private Long id;

    @NotNull(message = "Empresa proprietária é obrigatória")
    private Long empresaProprietariaId;

    @NotNull(message = "Tipo de dispositivo é obrigatório")
    private TipoDispositivo dispositivoTipo;

    @NotBlank(message = "Marca é obrigatória")
    @Size(max = 100, message = "Marca não pode ter mais que 100 caracteres")
    private String dispositivoMarca;

    @NotBlank(message = "Modelo é obrigatório")
    @Size(max = 100, message = "Modelo não pode ter mais que 100 caracteres")
    private String dispositivoModelo;

    @NotBlank(message = "Número de série é obrigatório")
    @Size(max = 50, message = "Número de série não pode ter mais que 50 caracteres")
    private String dispositivoNumeroSerie;

    private Boolean dispositivoAtivo = true;

    private LocalDateTime dispositivoDataInstalacao;

    private Long dispositivoFkUsuario;

    @Size(max = 1000, message = "Observações não podem ter mais que 1000 caracteres")
    private String dispositivoObservacoes;
}
