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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class YahooFinanceServiceImpl implements ExchangeService {

    @Override
    public CurrencyExchangeDTO exchange(String currencyFrom, String currencyTo, String currencyAmount) {
        RestTemplate restTemplate = new RestTemplate();
        BigDecimal targetAmount = restTemplate.getForEntity("https://query1.finance.yahoo.com/v7/finance/quote?symbols=" + currencyFrom + currencyTo + "%3DX",
                JsonNode.class).getBody().at("/quoteResponse/result/0/regularMarketPrice").decimalValue();
        return CurrencyExchangeDTO.builder()
                .sourceAmount(new BigDecimal(currencyAmount))
                .targetAmount(targetAmount.multiply(new BigDecimal(currencyAmount)))
                .sourceCurrency(currencyFrom)
                .targetCurrency(currencyTo)
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }
}
