package com.ecommerce.bex.model.valueobjects.user;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserInformation {
    @Getter
    private String username;

    @Embedded
    private Password password;

    @Embedded
    private Email email;

    @Embedded
    private CPF cpf;

    @Getter
    @Embedded
    private Address address;

    public String getEmail(){
        return email.getEmail();
    }

    public String getCpf(){
        return cpf.getCpf();
    }

    public String getPassword(){
        return password.getPassword();
    }
}
