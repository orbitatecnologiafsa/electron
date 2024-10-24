package com.electron.domain;
import com.electron.domain.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "municipios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "municipios_nome", nullable = false, length = 255)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "municipios_fk_estados", nullable = false)
    private Estado estado;
}
