package com.luizdev.order_management_system.exceptions;

public class NotFoundProductException extends RuntimeException {
    public NotFoundProductException(String message) {
        super(message);
    }
}
