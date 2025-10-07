package com.ecommerce.bex.model.valueobjects.user;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class CPF {
    private String cpf;

    public CPF(String cpf){
        this.cpf = cpf;
    }
}
