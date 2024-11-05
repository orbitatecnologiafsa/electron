package com.electron.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CepValidator implements ConstraintValidator<ValidCep, String> {

    private static final String CEP_REGEX = "^\\d{5}-?\\d{3}$";
    private static final Pattern CEP_PATTERN = Pattern.compile(CEP_REGEX);

    @Override
    public void initialize(ValidCep constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext context) {
        if (cep == null || cep.isEmpty()) {
            return false;
        }
        return CEP_PATTERN.matcher(cep).matches();
    }
}
