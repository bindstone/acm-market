package com.bindstone.acm.exchange.service;

import com.bindstone.acm.exchange.entity.ExchangeRate;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeRateService {

    List<ExchangeRate> getCurrencies();

    BigDecimal calculateExchange(String ccy, BigDecimal value);

    ExchangeRate getExchangeByCurrencyCode(String ccy);

}
