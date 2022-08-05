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

    @GetMapping("/getAll")
    public List<CoinResponseDTO> getALL() {
        return assetsService.getAll();
    }

    @GetMapping("/getById/{id}")
    public CoinResponseDTO getById(@PathVariable long id) {
        return assetsService.getById(id);
    }

    @GetMapping("/getByName/{name}")
    public CoinResponseDTO getByName(@PathVariable String name) {
        return assetsService.getByName(name);
    }

    @GetMapping("/getBySymbol/{symbol}")
    public CoinResponseDTO getBySymbol(@PathVariable String symbol) {
        return assetsService.getBySymbol(symbol);
    }

    @GetMapping("/getHistoryStart")
    public Flux<HistoryInfoDTO> getHistori(String symbol, String interval, Long start, Long end) {
        return assetsService.getAllHistory(symbol, interval, start, end);
    }

    @GetMapping("/getHistory")
    public Flux<HistoryInfoDTO> getHistori(String symbol, String interval) {
        return assetsService.getAllHistory(symbol, interval);
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
