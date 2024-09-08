package com.learn.api.config;

public class DuplicateInvoiceNumberException extends RuntimeException {
    public DuplicateInvoiceNumberException(String message) {
        super(message);
    }
}
