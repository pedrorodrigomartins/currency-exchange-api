package dev.pedrorodrigo.currencyexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public record ExchangeRateResponse(

        String result,

        @JsonProperty("time_last_update_utc")
        String timeLastUpdateUtc,

        @JsonProperty("base_code")
        String baseCode,

        @JsonProperty("conversion_rates")
        Map<String, BigDecimal> conversionRates

) {}
