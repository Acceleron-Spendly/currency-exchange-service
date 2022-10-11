package com.acceleron.spendly.exchange.core;

/*
 * Copyright (c) 2022 Acceleron Inc. (www.acceleron.com).
 * This software is property of Acceleron Inc. You may not
 * use this software or the resources of this software for
 * your own commercial purposes.
 * All rights reserved.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;

@ComponentScan("com.acceleron.spendly")
@SpringBootApplication
public class CurrencyExchangeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeServiceApplication.class);

        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate.getForEntity("https://query1.finance.yahoo.com/v7/finance/quote?symbols=USDUAH%3DX", String.class).getBody());
    }
}
