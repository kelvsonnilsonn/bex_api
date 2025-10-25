package com.ecommerce.bex.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Password {
    private String password;

    public static Password of(String password, PasswordEncoder encoder){
        return new Password(encoder.encode(password));
    }
}
