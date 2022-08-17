package com.cryptonita.app.integrationv2.services;

import com.cryptonita.app.dto.integration.CoinInfoIntegrationDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICoinService {

    Mono<CoinInfoIntegrationDTO> get(String coinID);

    Flux<CoinInfoIntegrationDTO> getAll(String... coinID);

}
