package com.electron.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfCnpjValidator implements ConstraintValidator<ValidCpfCnpj, String> {

    @Override
    public void initialize(ValidCpfCnpj constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cpfCnpj, ConstraintValidatorContext context) {
        if (cpfCnpj == null || cpfCnpj.isEmpty()) {
            return true;
        }

        cpfCnpj = cpfCnpj.replaceAll("\\D", "");

        if (cpfCnpj.length() == 11) {
            return validarCpf(cpfCnpj);
        } else if (cpfCnpj.length() == 14) {
            return validarCnpj(cpfCnpj);
        }

        return false;
    }

    private boolean validarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.chars().distinct().count() == 1) return false;

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        primeiroDigito = (primeiroDigito >= 10) ? 0 : primeiroDigito;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        segundoDigito = (segundoDigito >= 10) ? 0 : segundoDigito;

        return primeiroDigito == (cpf.charAt(9) - '0') && segundoDigito == (cpf.charAt(10) - '0');
    }

    private boolean validarCnpj(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || cnpj.chars().distinct().count() == 1) return false;

        int[] pesos = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;

        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * pesos[i];
        }
        int primeiroDigito = 11 - (soma % 11);
        primeiroDigito = (primeiroDigito >= 10) ? 0 : primeiroDigito;

        pesos = new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        soma = 0;

        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * pesos[i];
        }
        int segundoDigito = 11 - (soma % 11);
        segundoDigito = (segundoDigito >= 10) ? 0 : segundoDigito;

        return primeiroDigito == (cnpj.charAt(12) - '0') && segundoDigito == (cnpj.charAt(13) - '0');
    }
}
