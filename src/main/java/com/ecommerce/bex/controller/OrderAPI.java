package com.ecommerce.bex.controller;

import com.ecommerce.bex.command.order.CancelOrderCommand;
import com.ecommerce.bex.command.order.UpdateOrderStatusCommand;
import com.ecommerce.bex.dto.OrderResponseDTO;
import com.ecommerce.bex.dto.PageResponseDTO;
import com.ecommerce.bex.util.HttpConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Order", description = "API para gerenciamento de pedidos")
public interface OrderAPI {

    @Operation(summary = "Criar novo pedido", description = "Endpoint para criar um novo pedido a partir do carrinho do usuário")
    @ApiResponse(responseCode = HttpConstants.CREATED, description = "Pedido criado com sucesso")
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = HttpConstants.NOT_FOUND_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Long> order();

    @Operation(summary = "Cancelar pedido", description = "Endpoint para cancelar um pedido existente")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Pedido cancelado com sucesso")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = HttpConstants.NOT_FOUND_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Void> cancel(@RequestBody CancelOrderCommand command);

    @Operation(summary = "Listar meus pedidos", description = "Endpoint para listar todos os pedidos do usuário autenticado")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Pedidos listados com sucesso")
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<PageResponseDTO<OrderResponseDTO>> findMyOrders(Pageable pageable);

    @Operation(summary = "Atualizar status do pedido", description = "Endpoint para avançar o status do pedido para o próximo estágio")
    @ApiResponse(responseCode = HttpConstants.OK, description = "Status do pedido atualizado com sucesso")
    @ApiResponse(responseCode = HttpConstants.NOT_FOUND, description = HttpConstants.NOT_FOUND_MSG)
    @ApiResponse(responseCode = HttpConstants.BAD_REQUEST, description = HttpConstants.BAD_REQUEST_MSG)
    @ApiResponse(responseCode = HttpConstants.SERVER_ERROR, description = HttpConstants.INTERN_SERVER_ERROR_MSG)
    ResponseEntity<Void> updateOrderStatus(@RequestBody @Valid UpdateOrderStatusCommand command);
}