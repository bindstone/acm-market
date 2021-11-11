package com.bindstone.acm.market.service.exception;

import java.math.BigDecimal;

/**
 * Custom unhandled Exception for a business exception from the exchange-rate calculation.
 */
public class ExchangeRateRequestException extends RuntimeException {
    public ExchangeRateRequestException(String ccy, BigDecimal value) {
        super(String.format("Exchange Rate Request error for [%s] [%s]", ccy, value));
    }
}