package com.ecommerce.bex.command.order;

import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusCommand(
        @NotNull(message = AppConstants.ID_NOT_NULL_MESSAGE) Long orderId
) {
}
