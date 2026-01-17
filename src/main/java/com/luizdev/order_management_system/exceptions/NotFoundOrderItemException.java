package com.luizdev.order_management_system.exceptions;

public class NotFoundOrderItemException extends RuntimeException {
    public NotFoundOrderItemException(String message) {
        super(message);
    }
}
