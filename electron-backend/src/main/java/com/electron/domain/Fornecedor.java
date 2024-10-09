package com.electron.domain;

import com.electron.domain.enums.PForPJ;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fornecedores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PForPJ pfOuPj;

    @ManyToOne
    @JoinColumn(name = "registro_id", nullable = false)
    private Registro registro;
}
