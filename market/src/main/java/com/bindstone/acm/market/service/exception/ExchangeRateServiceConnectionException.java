package com.bindstone.acm.market.service.exception;

/**
 * Custom unhandled Exception for communication errors with the exchange rate service.
 */
public class ExchangeRateServiceConnectionException extends RuntimeException {

    public ExchangeRateServiceConnectionException() {
        super("Exchange-Rate Service not reachable");
    }
}