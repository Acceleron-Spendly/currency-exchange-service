package com.acceleron.spendly.exchange.core.services;
/*
 * Copyright (c) 2022 Acceleron Inc. (www.acceleron.com).
 * This software is property of Acceleron Inc. You may not
 * use this software or the resources of this software for
 * your own commercial purposes.
 * All rights reserved.
 */

import com.acceleron.spendly.exchange.api.dto.CurrencyExchangeDTO;
import com.acceleron.spendly.exchange.api.services.ExchangeService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.sql.Timestamp.from;
import static java.time.ZonedDateTime.now;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class ExchanGerateHostServiceImpl implements ExchangeService {

    private static final String RATES_JSON_KEY = "rates";
    private static final String URL_PATTERN = "https://api.exchangerate.host/latest?base=%s&symbols=%s&amount=%s&places=2";

    @Override
    public CurrencyExchangeDTO exchange(String currencyFrom, String currencyTo, String amount) {
        CurrencyExchangeDTO currencyExchangeDTO = mapToCurrencyExchangeDTO(currencyFrom, currencyTo, amount,
                getDataFromUrlRequest(URL_PATTERN.formatted(currencyFrom, currencyTo, amount)));

        log.debug("Currency exchange was requested: " + currencyExchangeDTO.toString());
        return currencyExchangeDTO;
    }

    private CurrencyExchangeDTO mapToCurrencyExchangeDTO(String currencyFrom, String currencyTo, String amount, JsonNode jsonNode) {
        return CurrencyExchangeDTO.builder()
                .sourceCurrency(currencyFrom)
                .targetCurrency(currencyTo)
                .sourceAmount(new BigDecimal(amount))
                .targetAmount(nonNull(jsonNode) ? jsonNode.get(RATES_JSON_KEY).get(currencyTo).decimalValue() : ZERO)
                .timestamp(from(now().toInstant()))
                .build();
    }

    private JsonNode getDataFromUrlRequest(String link) {
        return new RestTemplate().getForEntity(link, JsonNode.class).getBody();
    }
}
