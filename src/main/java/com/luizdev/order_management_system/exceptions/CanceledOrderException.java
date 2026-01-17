package com.luizdev.order_management_system.exceptions;

public class CanceledOrderException extends RuntimeException {
    public CanceledOrderException(String message) {
        super(message);
    }
}
