package com.cryptonita.app.integration.adapters;

import com.cryptonita.app.dto.integration.CoinMarketIntegrationDTO;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.stream.Collectors;

public interface ICoinMarketAdapter {

    default Flux<CoinMarketIntegrationDTO> getManyCoins() {
        return getManyCoins("usd");
    }

    Flux<CoinMarketIntegrationDTO> getManyCoins(String vs_currency);

    Flux<CoinMarketIntegrationDTO> getManyCoinsMetadata(String vs_currency, String ids, String category, String order, Integer per_page,
                                                        Integer page, Boolean sparkline, String price_change_percentage);

    default Flux<CoinMarketIntegrationDTO> getManyCoinsByIds(String vs_currency, String... ids) {
        return getManyCoinsByIds(vs_currency, String.join(",", ids));
    }

    Flux<CoinMarketIntegrationDTO> getManyCoinsByIds(String vs_currency, String ids);

}
