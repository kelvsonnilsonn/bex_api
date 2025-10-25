package com.ecommerce.bex.model.valueobjects.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private static final String EMAIL_REGEX =
            "^[a-zA-Z]+[a-zA-Z0-9._]+" +
                    "@[a-zA-Z]+[a-zA-Z0-9._]" +
                    "+\\.[a-zA-Z]{2,}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    private boolean validate(String email){
        return pattern.matcher(email).matches();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(email == null){
            return true;
        }

        return validate(email);
    }
}
