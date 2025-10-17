package com.ecommerce.bex.controller;

import com.ecommerce.bex.command.cart.AddItemToCartCommand;
import com.ecommerce.bex.command.cart.RemoveItemFromCartCommand;
import com.ecommerce.bex.dto.ItemResponseDTO;
import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Cart", description = "API para gerenciamento do carrinho de compras")
public interface CartAPI {

    @Operation(summary = "Adicionar item ao carrinho", description = "Endpoint para adicionar um item ao carrinho de compras")
    @ApiResponse(responseCode = HttpConstants.CREATED, description = "Item adicionado ao carrinho com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Void> addItem(@RequestBody AddItemToCartCommand command);

    @Operation(summary = "Remover item do carrinho", description = "Endpoint para remover um item do carrinho de compras")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Item removido do carrinho com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = HttpConstants.NOT_FOUND_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Void> removeItem(@RequestBody RemoveItemFromCartCommand command);

    @Operation(summary = "Limpar carrinho", description = "Endpoint para limpar todos os itens do carrinho de compras")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Carrinho limpo com sucesso")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Void> clearCart();

    @Operation(summary = "Listar itens do meu carrinho", description = "Endpoint para listar todos os itens do carrinho do usuário autenticado")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Itens listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<ItemResponseDTO>> findAllMyCartItems(Pageable pageable);
}