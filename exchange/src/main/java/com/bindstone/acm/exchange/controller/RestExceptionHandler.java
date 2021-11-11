package com.bindstone.acm.exchange.controller;

import com.bindstone.acm.exchange.service.exception.UnknownCurrencyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

// tag::asciidoc[]
/**
 * Global Exception Handler for as gate for the Exception and message transmitted by the Rest Request
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handler for an unknown currency
     * @param ex UnknownCurrencyException handled
     * @param request HTTP request
     * @return HTTP Response (400 - Bad request)
     */
    @ExceptionHandler(value = {UnknownCurrencyException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleUnknownCurrencyException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                httpHeadersSupplier.get(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Creating a Header with CrossOrigin
     */
    private final Supplier<HttpHeaders> httpHeadersSupplier = () -> {
        var header = new HttpHeaders();
        header.setAccessControlAllowMethods(List.of(HttpMethod.POST));
        header.setAccessControlAllowHeaders(List.of(HttpHeaders.CONTENT_TYPE));
        header.setAllow(Set.of(HttpMethod.GET, HttpMethod.HEAD, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS, HttpMethod.PATCH));
        return header;
    };
}
// end::asciidoc[]
