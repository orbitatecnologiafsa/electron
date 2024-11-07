package com.electron.domain.dtos;

import com.electron.domain.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDTO {

    private String nome;

    public Estado toEstado() {
        return new Estado(null, nome);
    }
}
