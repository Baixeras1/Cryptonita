package com.cryptonita.app.integration.services;

import com.cryptonita.app.dto.integration.CoinInfoIntegrationDTO;
import com.cryptonita.app.dto.integration.CoinMarketIntegrationDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICoinService {

    Mono<CoinInfoIntegrationDTO> getInfo(String coinID);

    Flux<CoinInfoIntegrationDTO> getAllInfos(String... coinID);

    Flux<CoinMarketIntegrationDTO> getAllMarkets();

    Flux<CoinMarketIntegrationDTO> getAllMarkets(String vs_currency);

    Flux<CoinMarketIntegrationDTO> getAllMarkets(String vs_currency, String ids, String category, String order, Integer per_page,
                                                 Integer page, Boolean sparkline, String price_change_percentage);

    Flux<CoinMarketIntegrationDTO> getAllMarkets(String vs_currency, String ids);

}
