package com.acceleron.spendly.exchange.api.services;
/*
 * Copyright (c) 2022 Acceleron Inc. (www.acceleron.com).
 * This software is property of Acceleron Inc. You may not
 * use this software or the resources of this software for
 * your own commercial purposes.
 * All rights reserved.
 */

import com.acceleron.spendly.exchange.api.dto.CurrencyExchangeDTO;

public interface ExchangeService {

    CurrencyExchangeDTO exchange(String currencyFrom, String currencyTo, String currencyAmount);
}
