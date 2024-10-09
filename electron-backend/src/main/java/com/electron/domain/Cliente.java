package com.electron.domain;

import com.electron.domain.enums.PForPJ;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private PForPJ pfOuPj;

    private Boolean revenda;

    @ManyToOne
    @JoinColumn(name = "registro_id", nullable = false)
    private Registro registro;

}
