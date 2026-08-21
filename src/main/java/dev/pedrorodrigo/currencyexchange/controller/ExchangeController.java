package dev.pedrorodrigo.currencyexchange.controller;

import dev.pedrorodrigo.currencyexchange.dto.ConversionResponse;
import dev.pedrorodrigo.currencyexchange.service.ExchangeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/convert")
    public ConversionResponse convert(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam BigDecimal amount
            ) {
        return exchangeService.convert(from, to, amount);
    }
}
