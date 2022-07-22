package com.acceleron.spendly.exchange.api.dto;

/*
 * Copyright (c) 2022 Acceleron Inc. (www.acceleron.com).
 * This software is property of Acceleron Inc. You may not
 * use this software or the resources of this software for
 * your own commercial purposes.
 * All rights reserved.
 */

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
public class CurrencyExchangeDTO {

    private long id;
    private Timestamp timestamp;

    private String sourceCurrency;
    private String targetCurrency;

    private BigDecimal sourceAmount;
    private BigDecimal targetAmount;
}
