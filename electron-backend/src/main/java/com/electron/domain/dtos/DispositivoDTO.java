package com.electron.domain.dtos;

import com.electron.domain.Dispositivo;
import com.electron.domain.EmpresaProprietaria;
import com.electron.domain.enums.TipoDispositivo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DispositivoDTO {
    private EmpresaProprietaria empresaProprietaria;

    private TipoDispositivo dispositivoTipo;

    private String dispositivoMarca;

    private String dispositivoModelo;

    private String dispositivoNumeroSerie;

    private Boolean dispositivoAtivo = true;

    private LocalDateTime dispositivoDataInstalacao;

    private Long dispositivoFkUsuario;

    private String dispositivoObservacoes;

    public Dispositivo toDispositivo() {
        return new Dispositivo(
                null, empresaProprietaria, dispositivoTipo, dispositivoMarca, dispositivoModelo, dispositivoNumeroSerie,
                dispositivoAtivo, dispositivoDataInstalacao, dispositivoFkUsuario, dispositivoObservacoes
        );
    }
}
