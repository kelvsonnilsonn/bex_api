package com.ecommerce.bex.command.coupon;

import com.ecommerce.bex.enums.DiscountType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateCouponCommand(
        @NotNull(message = "Código não pode ser nulo") String code,
        @NotNull(message = "Tipo não pode ser nulo") String type,
        @NotNull(message = "Valor não pode ser nulo") @Positive(message = "Valor de desconto deve ser maior que 0") BigDecimal discountValue,
        @NotNull(message = "Limite de uso não pode ser nulo") @Positive(message = "Limite deve ser maior que 0") int usageLimit,
        @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
        @NotNull(message = "Data de expiração não pode ser nula") LocalDateTime expiresAt
) {
}
