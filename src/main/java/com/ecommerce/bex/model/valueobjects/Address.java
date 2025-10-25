package com.ecommerce.bex.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Address {
    private String street;
    private String city;
    private String neighborhood;
    private String country;
    private int number;

    @Embedded
    private Zipcode zipcode;

    public Address(String country, String city, String neighborhood, String street, int number, Zipcode zipcode){
        this.country = country;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
    }

    public void setZipcode(String zipcode){
        this.zipcode = Zipcode.of(zipcode);
    }

    public String getZipcode(){
        return this.zipcode.getFormattedZipcode();
    }
}
