package com.electron.domain;

import com.electron.domain.enums.PForPJ;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Cliente extends Registro {

    @Enumerated(EnumType.STRING)
    private PForPJ pfOuPj;

    private Boolean revenda;
}
