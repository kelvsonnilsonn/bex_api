package com.ecommerce.bex.model.valueobjects.zipcode;

import com.ecommerce.bex.exception.InvalidZipcodeNumberException;

import java.util.regex.Pattern;

class ZipcodeFormatterValidate {

    private static final String ZIP_CODE_REGEX = "^\\d{5}-?\\d{3}$";
    private static final Pattern pattern = Pattern.compile(ZIP_CODE_REGEX);

    protected static String validate(String zipcode){
        String cleanedZipcode = zipcode.trim();

        if(!pattern.matcher(cleanedZipcode).matches()){
            throw new InvalidZipcodeNumberException();
        }

        return cleanedZipcode;
    }
}
