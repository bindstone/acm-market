package com.bindstone.acm.market.service.impl;

import com.bindstone.acm.market.service.exception.ExchangeRateRequestException;
import com.bindstone.acm.market.service.exception.ExchangeRateServiceConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

// tag::asciidoc[]
@Service
public class CurrencyRestServiceImpl implements com.bindstone.acm.market.service.CurrencyRestService {
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(CurrencyRestServiceImpl.class);
    @Value("${acm.exchange.server}")
    private String currencyServer;

    /**
     * Currency Rest Service
     * The implementation use a Spring Rest Template to retrieve the Data from the exchange Service.
     * An alternative to the Rest Template could be Retrofit.
     */
    public CurrencyRestServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Request calculation of exchange by external service
     * @param ccy product currency
     * @param value product value
     * @return EUR value calculated
     */
    @Override
    public BigDecimal calculateExchange(String ccy, BigDecimal value) {

        var url = String.format("%s/api/v1/exchange/%s/calculate/%s", currencyServer, ccy, value);
        logger.info("Request: {}", url);
        ResponseEntity<BigDecimal> response = null;
        try {
            response = restTemplate.getForEntity(url, BigDecimal.class);
        } catch (Exception e) {
            logger.error(String.format("Server error for request: %s", url), e);
            throw new ExchangeRateServiceConnectionException();
        }
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            logger.error(String.format("Error in calculating exchange for [%s][%f]", ccy, value));
            throw new ExchangeRateRequestException(ccy, value);
        }
    }
}
// end::asciidoc[]