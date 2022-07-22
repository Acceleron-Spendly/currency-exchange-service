package com.acceleron.spendly.exchange.controllers;

/*
 * Copyright (c) 2022 Acceleron Inc. (www.acceleron.com).
 * This software is property of Acceleron Inc. You may not
 * use this software or the resources of this software for
 * your own commercial purposes.
 * All rights reserved.
 */

import com.acceleron.spendly.exchange.api.services.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.acceleron.spendly.exchange.api.dto.CurrencyExchangeDTO;

import static com.acceleron.spendly.exchange.controllers.ExchangeController.CONTROLLER_PATH;

@RestController
@RequestMapping(CONTROLLER_PATH)
@RequiredArgsConstructor
public class ExchangeController {
    public static final String CONTROLLER_PATH = "/exchange";

    private final ExchangeService exchangeService;

    @GetMapping
    public CurrencyExchangeDTO getCurrencyExchange(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam(required = false, defaultValue = "1.00") String amount
    ) {
        return exchangeService.exchange(from, to, amount);
    }
}
