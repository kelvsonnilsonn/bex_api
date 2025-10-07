package com.ecommerce.bex.model.valueobjects.user;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Zipcode {
    private String zipcode;

    public Zipcode(String zipcode){
        this.zipcode = zipcode;
    }
}
