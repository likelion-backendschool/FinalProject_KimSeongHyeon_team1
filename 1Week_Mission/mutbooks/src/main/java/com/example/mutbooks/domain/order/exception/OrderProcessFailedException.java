package com.example.mutbooks.domain.order.exception;

public class OrderProcessFailedException extends RuntimeException {
    public OrderProcessFailedException(String message) {
        super(message);
    }
}
