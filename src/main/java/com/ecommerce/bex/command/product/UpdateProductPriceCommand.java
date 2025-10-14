package com.ecommerce.bex.command.product;

import java.math.BigDecimal;

public record UpdateProductPriceCommand(Long productId, BigDecimal newPrice) {
}
