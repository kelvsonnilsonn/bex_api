package com.ecommerce.bex.mapper;

import com.ecommerce.bex.command.RegisterCommand;
import com.ecommerce.bex.model.valueobjects.user.*;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default UserInformation toInformation(RegisterCommand dto, PasswordEncoder passwordEncoder) {
        Password password = Password.of(dto.password(), passwordEncoder);
        Email email = new Email(dto.email());
        CPF cpf = new CPF(dto.cpf());
        Zipcode zipcode = new Zipcode(dto.zipcode());
        Address address = new Address(dto.country(), dto.city(), dto.neighborhood(), dto.street(), dto.number(), zipcode);
        return new UserInformation(dto.username(), password, email, cpf, address);
    }
}
