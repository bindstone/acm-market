package com.bindstone.acm.market.service.exception;

/**
 * Custom unhandled Exception for an unknown product.
 */
public class UnknownProductException extends RuntimeException {

    public UnknownProductException(Long id) {
        super(String.format("Unknown product for ID: %d", id));
    }
}