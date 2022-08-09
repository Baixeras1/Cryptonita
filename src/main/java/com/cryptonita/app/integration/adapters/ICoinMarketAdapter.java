package com.cryptonita.app.integration.adapters;

import com.cryptonita.app.dto.integration.CoinMarketDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICoinMarketAdapter {

    /**
     * Returns the metadata of a certain coin
     *
     * @param symbol the symbol of the coin to search the metadata of
     * @return the dto with the metadata info
     */
    Mono<CoinMarketDTO> getCoinMetadataBySymbol(String symbol);

    /**
     * Returns the metadata of a certain coin
     *
     * @param name the name of the coin to search the metadata of
     * @return the dto with the metadata info
     */
    Mono<CoinMarketDTO> getCoinMetadataByName(String name);

    /**
     * Returns the metadata of a certain coin
     *
     * @param rank the rank of the coin to search the metadata of
     * @return the dto with the metadata info
     */
    Mono<CoinMarketDTO> getCoinMetadata(int rank);

    Flux<CoinMarketDTO> getManyCoinsMetadata(String manyDtos);

}
