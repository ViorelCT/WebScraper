package com.web.scraper.service;

import com.web.scraper.model.CurrencyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    public double getEurToRonRate() {

        String url =
                "https://api.frankfurter.dev/v1/latest?from=EUR&to=RON";

        RestTemplate restTemplate = new RestTemplate();

        CurrencyResponse response =
                restTemplate.getForObject(
                        url,
                        CurrencyResponse.class
                );

        if (response != null &&
                response.getRates() != null &&
                response.getRates().containsKey("RON")) {

            return response.getRates().get("RON");
        }

        return 5.0;
    }
}