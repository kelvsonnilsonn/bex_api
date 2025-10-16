package com.ecommerce.bex.model.valueobjects;

import com.ecommerce.bex.exception.ShortUsernameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Username {
    @Column(name="username", unique = true)
    private String username;

    public Username(String username){
        this.username = validate(username);
    }

    public static Username of(String username){
        return new Username(username);
    }

    private String validate(String username){
        Objects.requireNonNull(username, "O nome de usuário não pode ser nulo.");
        if(username.length() < 3){
            throw new ShortUsernameException();
        }
        return username;
    }
}
