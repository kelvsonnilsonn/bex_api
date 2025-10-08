package com.ecommerce.bex.model.valueobjects.user;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Embeddable
@Getter
@NoArgsConstructor
public class Password {
    private String password;

    public Password(String password){
        this.password = password;
    }

    public static Password of(String password, PasswordEncoder encoder){
        validate(password);
        return new Password(encoder.encode(password));
    }

    private static void validate(String string){
        if(string.length() < 3){
            throw new IllegalArgumentException();
        }
    }
}
