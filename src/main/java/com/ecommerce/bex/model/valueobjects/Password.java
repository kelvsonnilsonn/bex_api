package com.ecommerce.bex.model.valueobjects;

import com.ecommerce.bex.exception.InvalidPasswordException;
import com.ecommerce.bex.exception.ShortPasswordException;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

@Embeddable
@Getter
@NoArgsConstructor
public class Password {
    private String password;

    private static final String PASSWORD_REGEX =
            "^(?=.*\\d)(?=.*[A-Z])[a-zA-Z\\d!@#$%&_+*^~]+$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

    public Password(String password){
        this.password = password;
    }

    public static Password of(String password, PasswordEncoder encoder){
        validate(password);
        return new Password(encoder.encode(password));
    }

    private static void validate(String password){
        if(password.length() < 6){
            throw new ShortPasswordException();
        }
        if(!pattern.matcher(password).matches()){
            throw new InvalidPasswordException();
        }
    }
}
