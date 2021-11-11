package com.bindstone.acm.exchange.service.impl;

import com.bindstone.acm.exchange.entity.ExchangeRate;
import com.bindstone.acm.exchange.repository.ExchangeRateRepository;
import com.bindstone.acm.exchange.service.ExchangeRateService;
import com.bindstone.acm.exchange.service.exception.UnknownCurrencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

// tag::asciidoc[]

/**
 * Exchange Rate Service
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;
    private final Logger logger = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

    /**
     * Constructor with injected components
     * @param exchangeRateRepository Exchange Rate Repository
     */
    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    /**
     * Get the list of exchange rates
     * @return List of exchange rates
     */
    @Override
    @Transactional(readOnly = true)
    public List<ExchangeRate> getCurrencies() {
        logger.info("get exchange list");
        return exchangeRateRepository.findAll();
    }

    /**
     * Exchange a value in Euro
     * @param ccy base currency to exchange
     * @param value value to exchange
     * @return value exchanged in EUR
     */
    @Override
    @Transactional(readOnly = true)
    public BigDecimal calculateExchange(String ccy, BigDecimal value) {
        logger.info("calculate exchange [{}][{}]", ccy, value);
        var rate = getExchangeByCurrencyCode(ccy);
        return value.multiply(rate.getRate());
    }

    /**
     * Get an exchange rate for a requested currency
     * @param ccy currency in request
     * @return exchange rate for currency
     */
    @Override
    @Transactional(readOnly = true)
    public ExchangeRate getExchangeByCurrencyCode(String ccy) {
        logger.info("get exchange [{}]", ccy);
        return exchangeRateRepository.findByCcy(ccy)
                .orElseThrow(() -> new UnknownCurrencyException(ccy));
    }
}
// end::asciidoc[]
