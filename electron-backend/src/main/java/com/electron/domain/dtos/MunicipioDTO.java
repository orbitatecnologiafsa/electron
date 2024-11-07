package com.electron.domain.dtos;

import com.electron.domain.Estado;
import com.electron.domain.Municipio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioDTO {
    private String nome;

    private Estado estado;

    public Municipio toMunicipio() {
        return new Municipio(null, nome, estado);
    }
}
