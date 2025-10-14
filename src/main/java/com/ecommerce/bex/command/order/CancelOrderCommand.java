package com.ecommerce.bex.command.order;

public record CancelOrderCommand(Long orderId, String reason) {
}
