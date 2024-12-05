package com.electron.domain.dtos;

import com.electron.domain.Estado;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MunicipioDTO {

    private Long id;

    @Column(name = "municipios_nome", nullable = false)
    private String nome;

    private Long estadoId;
}
