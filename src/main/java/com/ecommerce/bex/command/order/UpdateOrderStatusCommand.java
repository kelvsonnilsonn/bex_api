package com.ecommerce.bex.command.order;

import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusCommand(
        @NotNull(message = "O ID do pedido é obrigatório") Long orderId
) {
}
