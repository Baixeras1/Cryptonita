package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IAssetsService;
import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import com.cryptonita.app.dto.integration.CandleInfoDTO;
import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

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

    @GetMapping("/getHistoriStart")
    public Flux<HistoryInfoDTO> getHistori(String symbol, String interval, Long start, Long end) {
        return assetsService.getAllHistori(symbol, interval, start, end);
    }

    @GetMapping("/getHistori")
    public Flux<HistoryInfoDTO> getHistori(String symbol, String interval) {
        return assetsService.getAllHistori(symbol, interval);
    }

    @GetMapping("/getCandle")
    public Flux<CandleInfoDTO> getCandle(String exchange, String interval, String baseId, String quoteId) {
        return assetsService.getAllCandles(exchange, interval, baseId, quoteId);
    }

    @GetMapping("/getCandleStart")
    public Flux<CandleInfoDTO> getCandle(String exchange, String interval, String baseId, String quoteId, long start, long end) {
        return assetsService.getAllCandles(exchange, interval, baseId, quoteId, start, end);
    }
}
