package com.ecommerce.bex.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

class CPFValidator implements ConstraintValidator<ValidCPF, String> {

    private static final String CPF_REGEX = "^\\d{3}[.]?\\d{3}[.]?\\d{3}-?\\d{2}$";
    private static final Pattern pattern = Pattern.compile(CPF_REGEX);

    private static String clearCPF(String cpf) {
        return cpf.replaceAll("[^0-9]", "");
    }

    private static boolean validLength(String cpf) {
        return cpf.length() == 11;
    }

    private static boolean validSequence(String cpf) {
        return !cpf.matches("(\\d)\\1{10}");
    }
    private static boolean validRegex(String cpf){
        return pattern.matcher(cpf).matches();
    }

    private static int validateFirstDigit(String cpf) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int rest = sum % 11;

        return (rest < 2) ? 0 : 11 - rest;
    }

    private static int validateSecondDigit(String cpf) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int rest = sum % 11;

        return (rest < 2) ? 0 : 11 - rest;
    }

    private static boolean validateDigits(String cpf) {
        int first_validator_digit = validateFirstDigit(cpf);
        int second_validator_digit = validateSecondDigit(cpf);

        return Character.getNumericValue(cpf.charAt(9)) == first_validator_digit
                && (Character.getNumericValue(cpf.charAt(10)) == second_validator_digit);
    }

    @Override
    public boolean isValid(String raw_cpf, ConstraintValidatorContext constraintValidatorContext) {
        if(raw_cpf == null) {
            return true;
        }

        if(!validRegex(raw_cpf)){
            return false;
        }

        String clearedCPF = clearCPF(raw_cpf);

        if(!validLength(clearedCPF) || !validSequence(clearedCPF)){
            return false;
        }

        return validateDigits(clearedCPF);
    }
}
