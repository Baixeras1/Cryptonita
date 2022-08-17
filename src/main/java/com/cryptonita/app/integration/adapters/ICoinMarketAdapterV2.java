package com.cryptonita.app.integration.adapters;

import com.cryptonita.app.dto.integration.CoinMarketDTO;
import com.cryptonita.app.dto.integration.CoinMarketIntegrationDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICoinMarketAdapterV2 {

    default Flux<CoinMarketIntegrationDTO> getManyCoins() {
        return getManyCoins("usd");
    }

    Flux<CoinMarketIntegrationDTO> getManyCoins(String vs_currency);

    Flux<CoinMarketIntegrationDTO> getManyCoinsMetadata(String vs_currency, String ids, String category, String order, Integer per_page,
                                                        Integer page, Boolean sparkline, String price_change_percentage);

    Flux<CoinMarketIntegrationDTO> getManyCoinsByIds(String vs_currency, String ids);
}
