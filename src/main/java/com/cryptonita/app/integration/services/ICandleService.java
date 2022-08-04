package com.cryptonita.app.integration.services;

import com.cryptonita.app.dto.integration.CandleInfoDTO;
import reactor.core.publisher.Flux;

public interface ICandleService {

    Flux<CandleInfoDTO> getAll(String exchange, String interval, String baseId, String quoteId, Long start, Long end);

    Flux<CandleInfoDTO> getAll(String exchange, String interval, String baseId, String quoteId);
}
