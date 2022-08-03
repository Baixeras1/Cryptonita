package com.cryptonita.app.integration.services.impl;

import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import com.cryptonita.app.integration.adapters.IHistoryAdapter;
import com.cryptonita.app.integration.services.IHistoryServiceInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class HistoryServiceImpl implements IHistoryServiceInfo {

    private final IHistoryAdapter historyAdapter;
    @Override
    public Flux<HistoryInfoDTO> getAll(String symbol, String interval, Long start, Long end) {
        return historyAdapter.getHistoryOfCoin(symbol, interval, start, end);
    }

    @Override
    public Flux<HistoryInfoDTO> getAll(String symbol, String interval) {
        return historyAdapter.getHistoryOfCoin(symbol, interval);
    }
}
