package com.cryptonita.app.integration.services;

import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import reactor.core.publisher.Flux;

public interface IHistoryServiceInfo {

    Flux<HistoryInfoDTO> getAll(String symbol, String interval, Long start, Long end);

    Flux<HistoryInfoDTO> getAll(String symbol, String interval);
}
