package com.ecommerce.bex.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserInformation {
    private Username username;

    @Embedded
    private Password password;

    @Embedded
    private Email email;

    @Embedded
    private CPF cpf;

    @Getter
    @Embedded
    private Address address;

    public void changeUsername(String newName){
        this.username = Username.of(newName);
    }

    public void changePassword(String newPassword, PasswordEncoder encoder){
        this.password = Password.of(newPassword, encoder);
    }

    public void changeEmail(String newEmail){
        this.email = Email.of(newEmail);
    }

    public String getUsername(){ return username.getUsername(); }

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
