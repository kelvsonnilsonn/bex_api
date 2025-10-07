package com.ecommerce.bex.enums;

import com.ecommerce.bex.exception.InvalidProductStatusException;
import com.ecommerce.bex.model.Product;

public enum ProductStatus {
    CREATED {
        @Override
        public void nextStatus(Product product) {
            product.setStatus(SOLD);
            product.setSoldDate();
        }
    },
    SOLD {
        @Override
        public void nextStatus(Product product) {
            product.setStatus(SENT);
            product.setSentDate();
        }
    },
    SENT {
        @Override
        public void nextStatus(Product product) {
            product.setStatus(RECEIVED);
            product.setReceivedDate();
        }
    },
    RECEIVED {
        @Override
        public void nextStatus(Product product) {
            throw new InvalidProductStatusException();
        }
    };

    public abstract void nextStatus(Product product);
}
