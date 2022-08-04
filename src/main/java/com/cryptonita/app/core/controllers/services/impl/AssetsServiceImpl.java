package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IAssetsService;
import com.cryptonita.app.data.providers.ICoinProvider;
import com.cryptonita.app.dto.integration.CandleInfoDTO;
import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import com.cryptonita.app.dto.response.CoinResponseDTO;
import com.cryptonita.app.integration.services.IHistoryServiceInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssetsServiceImpl implements IAssetsService {

    private final ICoinProvider coinProvider;

    private final IHistoryServiceInfo historyServiceInfo;

    @Override
    public List<CoinResponseDTO> getAll() {
        return null;
    }

    @Override
    public CoinResponseDTO getById(long id) {
        return null;
    }

    @Override
    public CoinResponseDTO getBySymbol(String symbol) {
        return null;
    }

    @Override
    public CoinResponseDTO getByName(String name) {
        return null;
    }

    @Override
    public List<HistoryInfoDTO> getAllHistorys(String symbol, String interval, Long start, Long end) {
        return null;
    }

    @Override
    public List<HistoryInfoDTO> getAllHistorys(String symbol, String interval) {
        return null;
    }

    @Override
    public List<CandleInfoDTO> getAllCandles(String exchange, String interval, String baseId, String quoteId, Long start, Long end) {
        return null;
    }

    @Override
    public List<CandleInfoDTO> getAllCandles(String exchange, String interval, String baseId, String quoteId) {
        return null;
    }
}
