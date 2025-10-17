package com.ecommerce.bex.controller;

import com.ecommerce.bex.command.product.*;
import com.ecommerce.bex.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Produtos", description = "API para gestão completa de produtos do e-commerce")
public interface ProductAPI {

    @Operation(summary = "Criar novo produto", description = "Endpoint para criar um novo produto no sistema com validação dos dados")
    @ApiResponse(responseCode = HttpConstants.CREATED, description = "Produto criado com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Long> create(@RequestBody @Valid CreateProductCommand dto);

    @Operation(summary = "Deletar produto", description = "Endpoint para deletar um produto do sistema")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Produto deletado com sucesso")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = HttpConstants.NOT_FOUND_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Void> delete(@RequestBody @Valid DeleteProductCommand command);

    @Operation(summary = "Atualizar nome do produto", description = "Endpoint para atualizar o nome de um produto")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Nome do produto atualizado com sucesso")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = HttpConstants.NOT_FOUND_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Void> updateName(@RequestBody @Valid UpdateProductNameCommand command);

    @Operation(summary = "Atualizar preço do produto", description = "Endpoint para atualizar o preço de um produto")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Preço do produto atualizado com sucesso")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = HttpConstants.NOT_FOUND_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Void> updatePrice(@RequestBody @Valid UpdateProductPriceCommand command);

    @Operation(summary = "Atualizar estoque do produto", description = "Endpoint para atualizar o estoque de um produto")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Estoque do produto atualizado com sucesso")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = HttpConstants.NOT_FOUND_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Void> updateStock(@RequestBody @Valid UpdateProductStockCommand command);

    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista paginada de todos os produtos cadastrados no sistema")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Lista de produtos retornada com sucesso")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<?> findProduct(Pageable pageable, @RequestParam(required = false) Long id, @RequestParam(required = false) String productCategory);
}