package com.ecommerce.bex.controller;

import com.ecommerce.bex.command.user.UpdateUserPasswordCommand;
import com.ecommerce.bex.dto.AuthResponseDTO;
import com.ecommerce.bex.command.LoginCommand;
import com.ecommerce.bex.command.RegisterCommand;
import com.ecommerce.bex.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Segurança", description = "Sistema de autenticação")
public interface AuthAPI {

    @Operation(summary = "Logar um usuário", description = "Loga de um usuário")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Login realizado com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    ResponseEntity<AuthResponseDTO> login(@RequestBody LoginCommand body);

    @Operation(summary = "Registrar um usuário", description = "Registra um usuário")
    @ApiResponse(responseCode = HttpConstants.CREATED, description = "Novo usuário criado com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterCommand body);

    @Operation(summary = "Alterar senha", description = "Altera senha de um usuário")
    @ApiResponse(responseCode = HttpConstants.CREATED, description = "Senha alterada com sucesso.")
    @ApiResponse(responseCode = HttpConstants.CONFLICT, description = HttpConstants.CONFLICT_MSG)
    ResponseEntity<Void> updatePassword(@RequestBody @Valid UpdateUserPasswordCommand password);

}
