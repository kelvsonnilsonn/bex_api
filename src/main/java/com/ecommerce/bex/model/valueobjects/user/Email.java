package com.ecommerce.bex.model.valueobjects.user;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Email {
    private String email;

    public Email(String email){
        this.email = email;
    }
}
