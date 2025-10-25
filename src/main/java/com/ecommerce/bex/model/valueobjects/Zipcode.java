package com.ecommerce.bex.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Zipcode {

    private String zipcode;

    public static Zipcode of(String zipcode){
        return new Zipcode(zipcode);
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