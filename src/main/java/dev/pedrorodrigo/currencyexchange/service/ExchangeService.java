package dev.pedrorodrigo.currencyexchange.service;

import dev.pedrorodrigo.currencyexchange.client.ExchangeRateClient;
import dev.pedrorodrigo.currencyexchange.dto.ConversionResponse;
import dev.pedrorodrigo.currencyexchange.dto.ExchangeRateResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExchangeService {

    private final ExchangeRateClient exchangeRateClient;

    public ExchangeService(ExchangeRateClient exchangeRateClient) {
        this.exchangeRateClient = exchangeRateClient;
    }

    public ConversionResponse convert(String from, String to, BigDecimal amount) {
        from = from.toUpperCase();
        to = to.toUpperCase();

        ExchangeRateResponse response = exchangeRateClient.getRates(from);

        BigDecimal rate = response.conversionRates().get(to);

        BigDecimal convertedAmount = amount.multiply(rate);

        return new ConversionResponse(
                from,
                to,
                amount,
                rate,
                convertedAmount,
                response.timeLastUpdateUtc()
        );
    }
}
