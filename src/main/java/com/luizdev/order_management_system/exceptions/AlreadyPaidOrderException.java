package com.luizdev.order_management_system.exceptions;

public class AlreadyPaidOrderException extends RuntimeException {
    public AlreadyPaidOrderException(String message) {
        super(message);
    }
}
