package com.ecommerce.bex.model.valueobjects;

import com.ecommerce.bex.command.user.UpdateUserAddressCommand;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Positive;
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

    public void setAddress(UpdateUserAddressCommand command){
        this.address.setAddress(command);
    }

    public String getAddressStreet(){
        return this.address.getStreet();
    }

    public String getAddressCountry(){
        return this.address.getCountry();
    }

    public String getAddressNeighborhood(){
        return this.address.getNeighborhood();
    }

    public String getAddressCity(){
        return this.address.getCity();
    }

    public int getAddressNumber(){
        return this.address.getNumber();
    }

    public String getAddressZipcode(){
        return this.address.getZipcode();
    }

}
