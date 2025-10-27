package com.ecommerce.bex.controller;

import com.ecommerce.bex.command.user.UpdateEmailCommand;
import com.ecommerce.bex.command.user.UpdateUserAddressCommand;
import com.ecommerce.bex.command.user.UpdateUsernameCommand;
import com.ecommerce.bex.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Usuários", description = "API para gestão completa de usuários do e-commerce")
public interface UserAPI {

    @Operation(summary = "Atualizar username do usuário autenticado", description = "Endpoint para atualizar o username do usuário atualmente autenticado")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Username atualizado com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Void> updateUsername(@RequestBody @Valid UpdateUsernameCommand command);

    @Operation(summary = "Atualizar email do usuário autenticado", description = "Endpoint para atualizar o email do usuário atualmente autenticado")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Email atualizado com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Void> updateEmail(@RequestBody @Valid UpdateEmailCommand command);

    @Operation(summary = "Atualizar endereço do usuário autenticado", description = "Endpoint para atualizar o endereço do usuário atualmente autenticado")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.UNAUTHORIZED, description = HttpConstants.UNAUTHORIZED_MSG)
    @ApiResponse(responseCode = HttpConstants.FORBIDDEN, description = HttpConstants.FORBIDDEN_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Void> updateAddress(@RequestBody @Valid UpdateUserAddressCommand command);
}