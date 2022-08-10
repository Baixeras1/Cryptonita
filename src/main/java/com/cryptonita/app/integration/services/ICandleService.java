package com.cryptonita.app.integration.services;

import com.cryptonita.app.dto.integration.CandleInfoDTO;
import reactor.core.publisher.Flux;

public interface ICandleService {

    Flux<CandleInfoDTO> getAll(String id, String vs_currency, String days);

}
