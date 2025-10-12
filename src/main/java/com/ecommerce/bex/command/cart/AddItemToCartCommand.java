package com.ecommerce.bex.command.cart;

public record AddItemToCartCommand (Long productId, Integer quantity) {

}