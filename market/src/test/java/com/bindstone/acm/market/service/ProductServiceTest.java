package com.bindstone.acm.market.service;

import com.bindstone.acm.market.entity.Product;
import com.bindstone.acm.market.repository.ProductRepository;
import com.bindstone.acm.market.service.exception.ExchangeRateRequestException;
import com.bindstone.acm.market.service.exception.ExchangeRateServiceConnectionException;
import com.bindstone.acm.market.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

// tag::asciidoc[]

/**
 * Product Test Suite, including a Mockup for connection with the external service EXCHANGE-SERVICE
 */
@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;

    CurrencyRestService currencyRestService;

    ProductService productService;

    /**
     * INSERT PRODUCT // SUCCESS
     */
    @Test
    public void getCurrenciesSuccessTest() {
        currencyRestService = Mockito.mock(CurrencyRestService.class);
        productService = new ProductServiceImpl(currencyRestService, productRepository);

        Mockito.when(currencyRestService.calculateExchange("USD", BigDecimal.valueOf(1))).thenReturn(BigDecimal.valueOf(3.5));

        var product = new Product();
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(1));
        product.setCurrency("USD");
        var calculate = productService.create(product);
        assertEquals(BigDecimal.valueOf(3.5), product.getPriceEur());
    }

    /**
     * Connection ERROR to external service
     */
    @Test
    public void getCurrenciesConnectionErrorTest() {
        currencyRestService = Mockito.mock(CurrencyRestService.class);
        productService = new ProductServiceImpl(currencyRestService, productRepository);

        Mockito.when(currencyRestService.calculateExchange("USD", BigDecimal.valueOf(1))).thenThrow(new ExchangeRateServiceConnectionException());

        var product = new Product();
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(1));
        product.setCurrency("USD");

        Exception exception = assertThrows(ExchangeRateServiceConnectionException.class, () -> {
            var calculate = productService.create(product);
        });
    }

    /**
     * INVALID Data transmitted to external service
     */
    @Test
    public void getCurrenciesDataErrorTest() {
        currencyRestService = Mockito.mock(CurrencyRestService.class);
        productService = new ProductServiceImpl(currencyRestService, productRepository);

        Mockito.when(currencyRestService.calculateExchange("FYI", BigDecimal.valueOf(1))).thenThrow(new ExchangeRateRequestException("FYI", BigDecimal.valueOf(1)));

        var product = new Product();
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(1));
        product.setCurrency("FYI");

        Exception exception = assertThrows(ExchangeRateRequestException.class, () -> {
            var calculate = productService.create(product);
        });
    }

    /**
     * Invalid DATA to save to Database (name to short)
     */
    @Test
    public void getCurrenciesConstraintErrorTest() {
        currencyRestService = Mockito.mock(CurrencyRestService.class);
        productService = new ProductServiceImpl(currencyRestService, productRepository);

        Mockito.when(currencyRestService.calculateExchange("USD", BigDecimal.valueOf(1))).thenReturn(BigDecimal.valueOf(3.5));

        var product = new Product();
        product.setName("T");
        product.setPrice(BigDecimal.valueOf(1));
        product.setCurrency("USD");

        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            var calculate = productService.create(product);
        });

        assertTrue(exception.getMessage().contains("Product name must be at least 2 and maximal 200 character"), "Invalid constraint message");
    }
}
// end::asciidoc[]