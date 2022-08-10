package com.cryptonita.app.integration.services.impl;

import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import com.cryptonita.app.integration.adapters.IHistoryAdapter;
import com.cryptonita.app.integration.services.IHistoryServiceInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class HistoryServiceInfoImpl implements IHistoryServiceInfo {

    private final IHistoryAdapter historyAdapter;
    @Override
    public Flux<HistoryInfoDTO> getAll(String id, String vs_currency, String days, String interval) {
        return historyAdapter.getHistoryOfCoin(id, interval, days, interval);
    }

    @Override
    public Flux<HistoryInfoDTO> getAll(String id, String vs_currency, String days) {
        return historyAdapter.getHistoryOfCoin(id,vs_currency, days);
    }
}
