package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IAssetsService;
import com.cryptonita.app.data.providers.ICoinProvider;
import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import com.cryptonita.app.dto.integration.CandleInfoDTO;
import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import com.cryptonita.app.integration.services.ICandleService;
import com.cryptonita.app.integration.services.IHistoryServiceInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@AllArgsConstructor
public class AssetsServiceImpl implements IAssetsService {

    private final ICoinProvider coinProvider;

    private final IHistoryServiceInfo historyServiceInfo;

    private final ICandleService candleService;

    @Override
    public List<CoinResponseDTO> getAll() {
        return coinProvider.getAllCoins();
    }

    @Override
    public CoinResponseDTO getById(long id) {
        return coinProvider.getCoinById(id);
    }

    @Override
    public CoinResponseDTO getBySymbol(String symbol) {
        return coinProvider.getBySymbol(symbol);
    }

    @Override
    public CoinResponseDTO getByName(String name) {
        return coinProvider.getCoinByName(name);
    }

    @Override
    public Flux<HistoryInfoDTO> getAllHistorys(String symbol, String interval, Long start, Long end) {
        return historyServiceInfo.getAll(symbol, interval, start, end);
    }

    @Override
    public Flux<HistoryInfoDTO> getAllHistorys(String symbol, String interval) {
        return historyServiceInfo.getAll(symbol, interval);
    }

    @Override
    public Flux<CandleInfoDTO> getAllCandles(String exchange, String interval, String baseId, String quoteId, Long start, Long end) {
        return candleService.getAll(exchange, interval, baseId, quoteId, start, end);
    }

    @Override
    public Flux<CandleInfoDTO> getAllCandles(String exchange, String interval, String baseId, String quoteId) {
        return candleService.getAll(exchange, interval, baseId, quoteId);
    }
}
