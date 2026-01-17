package com.luizdev.order_management_system.exceptions;

public class WithoutStockException extends RuntimeException {
    public WithoutStockException(String message) {
        super(message);
    }
}
