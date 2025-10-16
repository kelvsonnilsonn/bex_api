package com.ecommerce.bex.model.valueobjects;

import com.ecommerce.bex.exception.InvalidEmailException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor
@Getter
public class Email {
    @Column(name="email", unique = true)
    private String email;

    private static final String EMAIL_REGEX =
            "^[a-zA-Z]+[a-zA-Z0-9._]+" +
                    "@[a-zA-Z]+[a-zA-Z0-9._]" +
                    "+\\.[a-zA-Z]{2,}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public Email(String email){
        this.email = validate(email);
    }

    public static Email of(String email){
        return new Email(email);
    }

    private String validate(String email){
        if(!pattern.matcher(email).matches()){
            throw new InvalidEmailException();
        }
        return email;
    }
}
