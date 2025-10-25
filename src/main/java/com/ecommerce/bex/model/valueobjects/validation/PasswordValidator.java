package com.ecommerce.bex.model.valueobjects.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private static final String PASSWORD_REGEX =
            "^(?=.*\\d)(?=.*[A-Z])[a-zA-Z\\d!@#$%&_+*^~]+$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

    private static boolean validate(String password){
        return password.length() >= 6 && pattern.matcher(password).matches();
    }

    @Override
    public boolean isValid(String raw_password, ConstraintValidatorContext constraintValidatorContext) {
        if(raw_password == null){
            return true;
        }

        return validate(raw_password);
    }
}
