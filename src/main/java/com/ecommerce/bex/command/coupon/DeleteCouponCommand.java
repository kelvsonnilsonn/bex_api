package com.ecommerce.bex.command.coupon;

import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;

public record DeleteCouponCommand(
        @NotNull(message = AppConstants.ID_NOT_NULL_MESSAGE) Long couponId,
        String reason
) {
}
