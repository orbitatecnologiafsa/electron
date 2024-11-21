package com.electron.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubgrupoProdServDTO {
    private Long id;
    private String nome;
    private Long grupoProdServId;
}
