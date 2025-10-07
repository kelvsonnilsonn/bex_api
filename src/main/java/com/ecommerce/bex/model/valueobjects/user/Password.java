package com.ecommerce.bex.model.valueobjects.user;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Password {
    private String password;

    public Password(String password){
        this.password = password;
    }
}
