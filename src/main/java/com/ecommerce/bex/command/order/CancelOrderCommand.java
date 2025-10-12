package com.ecommerce.bex.command.order;

public record CancelOrderCommand(Long id, String reason) {
}
