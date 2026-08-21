package dev.pedrorodrigo.currencyexchange.client;

import dev.pedrorodrigo.currencyexchange.dto.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ExchangeRateClient {

    private final RestClient restClient;

    @Value("${exchange.api.key}")
    private String apiKey;

    public ExchangeRateClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public ExchangeRateResponse getRates(String baseCurrency) {
        return restClient.get()
                .uri("/{apiKey}/latest/{baseCurrency}", apiKey, baseCurrency)
                .retrieve()
                .body(ExchangeRateResponse.class);

    }
}
