package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imposto_cest_ncm")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TributoCest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tributo_cest_id")
    private Long id;

    @Column(name = "tributo_cest_codigo", unique = true, nullable = false, length = 8)
    private String codigo;

    @Column(name = "tributo_cest_descricao", columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cest_fk_ncm")
    private TributoNcm tributoNcm;

    public TributoCest(Long id) {
        this.id = id;
    }
}