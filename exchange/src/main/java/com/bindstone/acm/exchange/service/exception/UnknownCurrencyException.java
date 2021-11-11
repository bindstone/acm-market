package com.bindstone.acm.exchange.service.exception;

/**
 * Custom unhandled Exception for an unknown currency.
 */
public class UnknownCurrencyException extends RuntimeException {

    public UnknownCurrencyException(String currency) {
        super(String.format("Unknown currency: %s", currency));
    }

}
