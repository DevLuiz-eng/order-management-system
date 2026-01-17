package com.luizdev.order_management_system.exceptions;

public class AlreadyShippedOrderException extends RuntimeException {
    public AlreadyShippedOrderException(String message) {
        super(message);
    }
}
