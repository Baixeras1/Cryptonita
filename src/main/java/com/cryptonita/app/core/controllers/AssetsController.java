package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IAssetsService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/assets")
@CrossOrigin("*")
public class AssetsController {

    private final IAssetsService assetsService;

    @GetMapping("/getAll")
    public Mono<RestResponse> getAll() {
        return assetsService.getAll()
                .collectList()
                .map(RestResponse::encapsulate);
    }

    @GetMapping("/getById/{id}")
    public Mono<RestResponse> getById(@PathVariable long id) {
        return assetsService.getById(id)
                .map(RestResponse::encapsulate);
    }

    @GetMapping("/getByName/{name}")
    public Mono<RestResponse> getByName(@PathVariable String name) {
        return assetsService.getByName(name)
                .map(RestResponse::encapsulate);
    }

    @GetMapping("/getBySymbol/{symbol}")
    public Mono<RestResponse> getBySymbol(@PathVariable String symbol) {
        return assetsService.getBySymbol(symbol)
                .map(RestResponse::encapsulate);
    }

    @GetMapping("/getHistory")
    public Mono<RestResponse> getHistory(String symbol, String interval,
                                           @RequestParam Optional<Long> start,
                                           @RequestParam Optional<Long> end) {
        return assetsService.getAllHistory(symbol, interval, start, end)
                .collectList()
                .map(RestResponse::encapsulate);
    }

    @GetMapping("/getCandle")
    public Mono<RestResponse> getCandle(String exchange, String interval,
                                         String baseId, String quoteId,
                                         @RequestParam(required = false) Optional<Long> start,
                                         @RequestParam(required = false) Optional<Long> end) {
            return assetsService.getAllCandles(exchange, interval, baseId, quoteId, start, end)
                    .collectList()
                    .map(RestResponse::encapsulate);
        }


}
