package com.electron.domain;
import com.electron.domain.enums.PForPJ;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Fornecedor extends Pessoa {

    @Enumerated(EnumType.STRING)
    private PForPJ pfOuPj;

}