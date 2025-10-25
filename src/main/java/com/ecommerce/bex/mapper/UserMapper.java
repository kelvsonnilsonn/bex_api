package com.ecommerce.bex.mapper;

import com.ecommerce.bex.command.RegisterCommand;
import com.ecommerce.bex.dto.UserResponseDTO;
import com.ecommerce.bex.model.User;
import com.ecommerce.bex.model.valueobjects.*;
import com.ecommerce.bex.model.valueobjects.CPF;
import com.ecommerce.bex.model.valueobjects.zipcode.Zipcode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "username", expression = "java(user.getUsername())")
    UserResponseDTO toResponse(User user);

    default UserInformation toInformation(RegisterCommand dto, PasswordEncoder passwordEncoder) {
        Password password = Password.of(dto.password(), passwordEncoder);
        Email email = new Email(dto.email());
        CPF cpf = new CPF(dto.cpf());
        Zipcode zipcode = new Zipcode(dto.zipcode());
        Address address = new Address(dto.country(), dto.city(), dto.neighborhood(), dto.street(), dto.number(), zipcode);
        Username username = new Username(dto.username());
        return new UserInformation(username, password, email, cpf, address);
    }
}
