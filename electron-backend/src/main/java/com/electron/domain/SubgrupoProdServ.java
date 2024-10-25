package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subgrupo_prod_serv")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubgrupoProdServ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subgrupo_prod_serv_id")
    private Long id;

    @Column(name = "subgrupo_prod_serv_nome", length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "subgrupo_fk_prod_serv", referencedColumnName = "grupo_prod_serv_id")
    private GrupoProdServ grupoProdServ;
}