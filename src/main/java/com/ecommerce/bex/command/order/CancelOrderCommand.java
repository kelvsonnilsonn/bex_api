package com.ecommerce.bex.command.order;

import jakarta.validation.constraints.NotNull;

public record CancelOrderCommand(
        @NotNull Long orderId,
        String reason
) {
}
