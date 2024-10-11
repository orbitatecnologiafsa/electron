package com.electron.domain.enums;

public enum Cargo {
    USUARIO("usuario"),
    ADMIN("admin"),
    MODERADOR("moderador");

    private String cargo;

    Cargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

}
