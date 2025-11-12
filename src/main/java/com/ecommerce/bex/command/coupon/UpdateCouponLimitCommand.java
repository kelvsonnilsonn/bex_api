package com.ecommerce.bex.command.coupon;

import com.ecommerce.bex.util.AppConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateCouponLimitCommand(
        @NotNull(message = AppConstants.ID_NOT_NULL_MESSAGE) Long couponId,
        @PositiveOrZero int newUsageLimit
) {
}
