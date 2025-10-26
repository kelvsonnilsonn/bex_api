package com.ecommerce.bex.model.valueobjects;

import com.ecommerce.bex.command.user.UpdateUserAddressCommand;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Setter
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

    public void setAddress(UpdateUserAddressCommand command){
        if(command.street() != null){
            setStreet(command.street());
        }
        if(command.city() != null){
            setCountry(command.city());
        }
        if(command.country() != null){
            setCity(command.country());
        }
        if(command.neighborhood() != null){
            setNeighborhood(command.neighborhood());
        }

        setNumber(command.number());

        if(command.zipcode() != null){
            setZipcode(command.zipcode());
        }
    }

    public void setZipcode(String zipcode){
        this.zipcode = Zipcode.of(zipcode);
    }

    public String getZipcode(){
        return this.zipcode.getFormattedZipcode();
    }
}
