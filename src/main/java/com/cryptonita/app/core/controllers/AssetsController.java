package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IAssetsService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/assets")
@CrossOrigin("*")
@Tag(name = "Assets")
public class AssetsController {

    private final IAssetsService assetsService;

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get current data (name, price, market, ... including exchange tickers) for all the coins supported")
    public Mono<RestResponse> getAll() {
        return assetsService.getAll()
                .collectList()
                .map(RestResponse::encapsulate);
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get current data (name, price, market, ... including exchange tickers) for a coin by id")
    public Mono<RestResponse> getById(@PathVariable long id) {
        return assetsService.getById(id)
                .map(RestResponse::encapsulate);
    }

    @GetMapping("/getByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get current data (name, price, market, ... including exchange tickers) for a coin by name")
    public Mono<RestResponse> getByName(@PathVariable String name) {
        return assetsService.getByName(name)
                .map(RestResponse::encapsulate);
    }

    @GetMapping("/getBySymbol/{symbol}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get current data (name, price, market, ... including exchange tickers) for a coin by symbol")
    public Mono<RestResponse> getBySymbol(@PathVariable String symbol) {
        return assetsService.getBySymbol(symbol)
                .map(RestResponse::encapsulate);
    }

    @GetMapping("/getHistory")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get historical data (name, price, market, stats) at a given date for a coin")
    public Mono<RestResponse> getHistory(String id, String vs_currency,
                                           String days,
                                           @RequestParam Optional<String> interval) {

        return assetsService.getAllHistory(id, vs_currency, days, interval)
                .collectList()
                .map(RestResponse::encapsulate);
    }

    @GetMapping("/getCandle")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get historical ohlc at a given date range for a coin")
    public Mono<RestResponse> getCandle(String id, String vs_currency, String days) {
            return assetsService.getAllCandles(id, vs_currency, days)
                    .collectList()
                    .map(RestResponse::encapsulate);
        }


}
