package com.ecommerce.bex.model.valueobjects.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

class ZipcodeValidator implements ConstraintValidator<ValidZipcode, String> {

    private static final String ZIP_CODE_REGEX = "^\\d{5}-?\\d{3}$";
    private static final Pattern pattern = Pattern.compile(ZIP_CODE_REGEX);

    protected static boolean validate(String zipcode){
        String cleanedZipcode = zipcode.trim();

        return pattern.matcher(cleanedZipcode).matches();
    }

    @Override
    public boolean isValid(String raw_zipcode, ConstraintValidatorContext constraintValidatorContext) {
        if(raw_zipcode == null){
            return true;
        }
        return validate(raw_zipcode);
    }
}
