package com.electron.domain;

import com.electron.domain.enums.PForPJ;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "transportadores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transportador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PForPJ pfOuPj;

    @OneToMany(mappedBy = "transportadora")
    private List<Veiculo> veiculos;

    @ManyToOne
    @JoinColumn(name = "registro_id", nullable = false)
    private Registro registro;
}
