package com.bindstone.acm.exchange.service;

import com.bindstone.acm.exchange.entity.ExchangeRate;
import com.bindstone.acm.exchange.service.exception.UnknownCurrencyException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// tag::asciidoc[]
/**
 * Echange Rate Test Suite
 */
@SpringBootTest
class ExchangeRangeServiceTest {

    @Autowired
    private ExchangeRateService exchangeRateService;

    /**
     * Retrieve a list of current exchange rate (SUCCESS)
     */
    @Test
    public void getCurrenciesTest() {
        List<ExchangeRate> currencies = exchangeRateService.getCurrencies();
        assertFalse(currencies.isEmpty(), "No currencies in the list (Empty List)");
    }

    /**
     * Retrieve a currency for an existing CCY Code (SUCCESS)
     */
    @Test
    public void getByExchangeCodeSuccessTest() {
        var exchange = exchangeRateService.getExchangeByCurrencyCode("USD");
        assertEquals(new BigDecimal(1.3, MathContext.DECIMAL32), exchange.getRate());
    }

    /**
     * Retrieve a currency for an non-existing CCY Code (ERROR)
     */
    @Test
    public void getByExchangeCodeErrorTest() {
        Exception exception = assertThrows(UnknownCurrencyException.class, () -> exchangeRateService.getExchangeByCurrencyCode("FYI"));
        assertNotNull(exception, "No exception thrown");
        assertNotNull(exception.getMessage(), "No message in the exception");
        assertTrue(exception.getMessage().contains("FYI"), "Currency not included in message");
    }

    /**
     * calculating an exchange for a given currency and value (SUCCESS)
     */
    @Test
    public void calculateExchangeTest() {
        var result = exchangeRateService.calculateExchange("USD", new BigDecimal(2));
        assertEquals(new BigDecimal(2.6, MathContext.DECIMAL32), result, "value not correct exchanged");
    }

    /**
     * calculating an exchange for a given currency and value (ERROR)
     */
    @Test
    public void calculateExchangeUnknownCurrencyTest() {
        Exception exception = assertThrows(UnknownCurrencyException.class, () -> exchangeRateService.calculateExchange("FYI", new BigDecimal(2)));
        assertNotNull(exception, "No exception thrown");
        assertNotNull(exception.getMessage(), "No message in the exception");
        assertTrue(exception.getMessage().contains("FYI"), "Currency not included in message");
    }
}
// end::asciidoc[]