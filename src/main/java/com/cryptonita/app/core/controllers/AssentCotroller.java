package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IAssetsService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/assent")
public class AssentCotroller {

    private final IAssetsService assetsService;

    @CrossOrigin("*")
    @GetMapping("/getAll")
    public RestResponse getALL() {
        return RestResponse.encapsulate(assetsService.getAll());
    }

    @GetMapping("/getById/{id}")
    public RestResponse getById(@PathVariable long id) {
        return RestResponse.encapsulate(assetsService.getById(id));
    }

    @GetMapping("/getByName/{name}")
    public RestResponse getByName(@PathVariable String name) {
        return RestResponse.encapsulate(assetsService.getByName(name));
    }

    @GetMapping("/getBySymbol/{symbol}")
    public RestResponse getBySymbol(@PathVariable String symbol) {
        return RestResponse.encapsulate(assetsService.getBySymbol(symbol));
    }

    @GetMapping("/getHistory")
    public Flux<RestResponse> getHistory(String symbol, String interval,
                                           @RequestParam Optional<Long> start,
                                           @RequestParam Optional<Long> end) {
        return assetsService.getAllHistory(symbol, interval, start, end)
                .map(RestResponse::encapsulate);
    }

    @GetMapping("/getCandle")
    public Flux<RestResponse> getCandle(String exchange, String interval,
                                         String baseId, String quoteId,
                                         @RequestParam(required = false) Optional<Long> start,
                                         @RequestParam(required = false) Optional<Long> end) {
            return assetsService.getAllCandles(exchange, interval, baseId, quoteId, start, end)
                    .map(RestResponse::encapsulate);
        }


}
