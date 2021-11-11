package com.bindstone.acm.exchange.repository;

import com.bindstone.acm.exchange.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// tag::asciidoc[]
/**
 * Repository interface to Query the Exchange Rate from DB
 */
@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    /**
     * Retrieve an exchange rate from Database in case it exists, using Spring-JPA naming convention for the method.
     * An other option @Query and writing the select statement in JPA Query language.
     * @param ccy currency for request
     * @return Optional exchange rate
     */
    Optional<ExchangeRate> findByCcy(String ccy);
}
// end::asciidoc[]
