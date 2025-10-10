package com.ecommerce.bex.enums;

import com.ecommerce.bex.exception.ProductAlreadyReceivedException;
import com.ecommerce.bex.model.Order;

public enum OrderStatus {
    PENDING{
        @Override
        public void nextStatus(Order order) {
            order.setStatus(PAID);
        }
    },
    PAID{
        @Override
        public void nextStatus(Order order) {
            order.setStatus(SENT);
        }
    },
    SENT{
        @Override
        public void nextStatus(Order order) {
            order.setStatus(RECEIVED);
        }
    },
    RECEIVED{
        @Override
        public void nextStatus(Order order) {
            throw new ProductAlreadyReceivedException();
        }
    };

    public abstract void nextStatus(Order order);

}
