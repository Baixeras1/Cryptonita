package com.cryptonita.app.integration.adapters;

import com.cryptonita.app.dto.integration.CoinMarketDTO;
import com.cryptonita.app.dto.integration.CoinMarketIntegrationDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICoinMarketAdapterV2 {

    /**
     * Returns the metadata of a certain coin
     *
     * @param name the name of the coin to search the metadata of
     * @return the dto with the metadata info
     */
    Flux<CoinMarketIntegrationDTO> getCoinMetadataByName(String vs_currency);

    Flux<CoinMarketIntegrationDTO> getManyCoinsMetadata(String vs_currency, String ids, String category, String order, Integer per_page,
                                                        Integer page, Boolean sparkline, String price_change_percentage);
}
