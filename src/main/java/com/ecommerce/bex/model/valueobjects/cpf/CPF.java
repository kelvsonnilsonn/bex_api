package com.ecommerce.bex.model.valueobjects.cpf;

import com.ecommerce.bex.exception.InvalidCPFNumberException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor
@Getter
public class CPF {
    @Column(name="cpf", unique = true)
    private String cpf;

    public CPF(String cpf){
        this.cpf = CPFFormatter.validateFormatter(cpf);
        CPFCalculateDigits.validateDigits(this.cpf);
    }
}
