package com.ecommerce.bex.command.coupon;

import com.ecommerce.bex.util.AppConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateCouponExpireDateCommand(
        @NotNull(message = AppConstants.ID_NOT_NULL_MESSAGE) Long couponId,
        @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
        LocalDateTime newExpireDate
) {
}
