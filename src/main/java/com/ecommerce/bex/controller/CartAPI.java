package com.ecommerce.bex.controller;

import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.dto.cart.CartAddRequestDTO;
import com.ecommerce.bex.dto.cart.CartResponseDTO;
import com.ecommerce.bex.dto.cart.ItemResponseDTO;
import com.ecommerce.bex.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Cart", description = "API para gerenciamento do carrinho de compras")
public interface CartAPI {

    @Operation(summary = "Adicionar item ao carrinho", description = "Endpoint para adicionar um item ao carrinho de compras")
    @ApiResponse(responseCode = HttpConstants.CREATED, description = "Item adicionado ao carrinho com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<String> addItem(@RequestBody CartAddRequestDTO dto);

    @Operation(summary = "Listar itens do meu carrinho", description = "Endpoint para listar todos os itens do carrinho do usuário autenticado")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Itens listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<ItemResponseDTO>> findAllMyCartItems(Pageable pageable);

    @Operation(summary = "Listar todos os carrinhos", description = "Endpoint para listar todos os carrinhos do sistema (admin)")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Carrinhos listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<CartResponseDTO>> findAll(Pageable pageable);

    @Operation(summary = "Buscar produtos de um carrinho", description = "Endpoint para buscar produtos de um carrinho específico")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Produtos do carrinho listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = HttpConstants.NOT_FOUND_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<ItemResponseDTO>> findCartProducts(Pageable pageable, @PathVariable Long id);
}