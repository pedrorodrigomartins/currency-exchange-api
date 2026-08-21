package dev.pedrorodrigo.currencyexchange.service;

import dev.pedrorodrigo.currencyexchange.client.ExchangeRateClient;
import dev.pedrorodrigo.currencyexchange.dto.ConversionResponse;
import dev.pedrorodrigo.currencyexchange.dto.ExchangeRateResponse;
import dev.pedrorodrigo.currencyexchange.exception.CurrencyNotFoundException;
import dev.pedrorodrigo.currencyexchange.exception.InvalidAmountException;
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

        if (rate == null) {
            throw new CurrencyNotFoundException("Currency not found: " + to);
        }

        BigDecimal convertedAmount = amount.multiply(rate);

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException(
                    "Amount must be greater than zero"
            );
        }

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
