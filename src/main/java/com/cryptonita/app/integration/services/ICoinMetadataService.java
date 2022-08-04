package com.cryptonita.app.integration.services;

import com.cryptonita.app.dto.integration.CoinMetadataDTO;
import reactor.core.publisher.Mono;

public interface ICoinMetadataService {

     Mono<CoinMetadataDTO> getCoinMetadataBySymbol(String symbol);

     Mono<CoinMetadataDTO> getCoinMetadataByName(String name);

     Mono<CoinMetadataDTO> getCoinMetadataByRank(int rank);

}
