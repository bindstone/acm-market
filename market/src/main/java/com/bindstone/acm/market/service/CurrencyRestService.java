package com.bindstone.acm.market.service;

import java.math.BigDecimal;

public interface CurrencyRestService {

    BigDecimal calculateExchange(String ccy, BigDecimal value);

}
