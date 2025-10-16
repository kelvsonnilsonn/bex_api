package com.ecommerce.bex.model;

import com.ecommerce.bex.enums.UserRole;
import com.ecommerce.bex.model.valueobjects.Address;
import com.ecommerce.bex.model.valueobjects.UserInformation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserInformation userInformation;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private LocalDateTime createdAt;

    public User(UserInformation userInformation){
        this.userInformation = userInformation;
        this.createdAt = LocalDateTime.now();
        this.role = UserRole.USER_ROLE;
    }

    public void changeUsername(String newName){
        this.userInformation.changeUsername(newName);
    }

    public void changePassword(String newPassword, PasswordEncoder encoder){
        this.userInformation.changePassword(newPassword, encoder);
    }

    public void changeEmail(String newEmail){
        this.userInformation.changeEmail(newEmail);
    }

    public String getUsername() {
        return userInformation.getUsername();
    }

    public String getEmail(){
        return userInformation.getEmail();
    }

    public String getCpf(){
        return userInformation.getCpf();
    }

    public String getPassword(){
        return userInformation.getPassword();
    }

    public Address getAddress() {
        return userInformation.getAddress();
    }
}
