package com.electron.domain.dtos;

import com.electron.domain.enums.Cargo;

public record RegistroDTO(String email, String senha, String nome, Cargo cargo) {
    public RegistroDTO {
        if (cargo == null) {
            cargo = Cargo.USUARIO;
        }
    }
}
