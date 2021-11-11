package com.bindstone.acm.exchange.controller;

import com.bindstone.acm.exchange.entity.ExchangeRate;
import com.bindstone.acm.exchange.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

// tag::asciidoc[]

/**
 * Rest Controller for Exchange Rate
 * - Entry Point: /api/v1/exchange
 * - CrossOrigin for UI to access to information
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/exchange")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    /**
     * Get Exchange List
     * @return Exchange list (Name and Value)
     */
    @GetMapping
    @Operation(summary = "Retrieve the list of current currencies")
    public ResponseEntity<List<ExchangeRate>> getExchangeRateList() {
        return ResponseEntity.ok(exchangeRateService.getCurrencies());
    }

    /**
     * Get an EUR value for a given currency and value
     * @param ccy base currency
     * @param value base value
     * @return EUR value calculated
     */
    @GetMapping("/{ccy}/calculate/{value}")
    @Operation(summary = "Calculate the exchange value to EURO for a given amount and currency (ISO code)")
    public ResponseEntity<BigDecimal> calculate(
            @Parameter(description = "Currency in ISO format")
            @PathVariable String ccy,
            @Parameter(description = "Value to change to EUR as Decimal")
            @PathVariable BigDecimal value) {
        var calculateExchange = exchangeRateService.calculateExchange(ccy, value);
        return ResponseEntity.ok(calculateExchange);
    }

    /**
     * Get an exchange value for given currency
     * @param ccy base currency
     * @return exchange value for the ccy
     */
    @GetMapping("/{ccy}")
    @Operation(summary = "Retrieve the current currency for a given currency ISO code")
    public ResponseEntity<ExchangeRate> getExchangeRate(
            @Parameter(description = "Currency in ISO format")
            @PathVariable String ccy
    ) {
        var exchangeRate = exchangeRateService.getExchangeByCurrencyCode(ccy);
        return ResponseEntity.ok(exchangeRate);
    }
// end::asciidoc[]

    /**
     *  Alternative mapping methode of REST-URL, by PathVariable. Depend on the company organisation,
     *  important is it should be the same structure for all the products.
     *
     *  Main difference
     *       @PathVariable value is not encoded /api/v1/exchange/usd
     *       @RequestParam value is encoded     /api/v1/exchange?code=usd
     *
     * @GetMapping
     * public ResponseEntity<ExchangeRate> getExchangeRate(@RequestParam String ccy) {
     *    return ResponseEntity.ok(exchangeRateService.getByExchangeCode(ccy));
     * }
     */
}

