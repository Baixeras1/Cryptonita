package com.cryptonita.app.integration.adapters;

import com.cryptonita.app.dto.integration.CoinMetadataDTO;
import reactor.core.publisher.Mono;

public interface ICoinMetaAdapter {

    /**
     * Returns the metadata of a certain coin
     *
     * @param symbol the symbol of the coin to search the metadata of
     * @return the dto with the metadata info
     */
    Mono<CoinMetadataDTO> getCoinMetadataBySymbol(String symbol);

    /**
     * Returns the metadata of a certain coin
     *
     * @param name the name of the coin to search the metadata of
     * @return the dto with the metadata info
     */
    Mono<CoinMetadataDTO> getCoinMetadataByName(String name);

    /**
     * Returns the metadata of a certain coin
     *
     * @param rank the rank of the coin to search the metadata of
     * @return the dto with the metadata info
     */
    Mono<CoinMetadataDTO> getCoinMetadata(int rank);

}
