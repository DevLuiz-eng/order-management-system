package com.luizdev.order_management_system.exceptions;

public class UnpaidOrderException extends RuntimeException {
    public UnpaidOrderException(String message) {
        super(message);
    }
}
