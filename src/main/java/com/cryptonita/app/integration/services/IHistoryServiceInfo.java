package com.cryptonita.app.integration.services;

import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import reactor.core.publisher.Flux;

public interface IHistoryServiceInfo {

    Flux<HistoryInfoDTO> getAll(String id, String vs_currency, String days, String interval);

    Flux<HistoryInfoDTO> getAll(String id, String vs_currency, String days);
}
