package com.electron.domain.enums;

public enum TipoEmpresa {
    PESSOA_FISICA("Pessoa Física"),
    PESSOA_JURIDICA("Pessoa Jurídica");

    private final String descricao;

    TipoEmpresa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}