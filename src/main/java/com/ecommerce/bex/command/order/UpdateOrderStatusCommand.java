package com.ecommerce.bex.command.order;

import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusCommand(
        @NotNull Long orderId
) {
}
