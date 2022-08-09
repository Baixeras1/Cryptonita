package com.cryptonita.app.integration.services;

import com.cryptonita.app.dto.integration.CoinMarketDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICoinMarketService {

     Mono<CoinMarketDTO> getCoinMetadataBySymbol(String symbol);

     Mono<CoinMarketDTO> getCoinMetadataByName(String name);

     Mono<CoinMarketDTO> getCoinMetadataByRank(int rank);

     Flux<CoinMarketDTO> getManyCoinsMeta(String manyCoins);

}
