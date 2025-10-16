package com.ecommerce.bex.model.valueobjects.zipcode;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Zipcode {
    private String zipcode;

    public Zipcode(String zipcode){
        this.zipcode = zipcode;
    }

    public static Zipcode of(String zipcode){
        return new Zipcode(ZipcodeFormatterValidate.validate(zipcode));
    }

    public String getFormattedZipcode() {
        if (zipcode.contains("-")) {
            return zipcode;
        }
        return zipcode.substring(0, 5) + "-" + zipcode.substring(5);
    }

    @Override
    public String toString() {
        return getFormattedZipcode();
    }
}