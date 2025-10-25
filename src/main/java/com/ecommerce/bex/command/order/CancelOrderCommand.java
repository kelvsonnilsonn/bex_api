package com.ecommerce.bex.command.order;

import jakarta.validation.constraints.NotNull;

public record CancelOrderCommand(
        @NotNull(message = "O ID do pedido é obrigatório") Long orderId,
        String reason
) {
}
