package com.cryptonita.app.integration.services.impl;

import com.cryptonita.app.dto.integration.CoinInfoIntegrationDTO;
import com.cryptonita.app.dto.integration.CoinMarketIntegrationDTO;
import com.cryptonita.app.integration.adapters.ICoinMarketAdapter;
import com.cryptonita.app.integration.adapters.ICoinInfoAdapter;
import com.cryptonita.app.integration.services.ICoinService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CoinServiceImpl implements ICoinService {

    private final ICoinInfoAdapter infoAdapter;
    private final ICoinMarketAdapter coinMarketAdapterV2;

    @Override
    public Mono<CoinInfoIntegrationDTO> getInfo(String coinID) {
        return infoAdapter.get(coinID);
    }

    @Override
    public Flux<CoinInfoIntegrationDTO> getAllInfos(String... coinIDs) {
        return infoAdapter.get(coinIDs);
    }

    @Override
    public Flux<CoinMarketIntegrationDTO> getAllMarkets() {
        return coinMarketAdapterV2.getManyCoins();
    }

    @Override
    public Flux<CoinMarketIntegrationDTO> getAllMarkets(String vs_currency) {
        return coinMarketAdapterV2.getManyCoins(vs_currency);
    }

    @Override
    public Flux<CoinMarketIntegrationDTO> getAllMarkets(String vs_currency, String ids, String category, String order, Integer per_page, Integer page, Boolean sparkline, String price_change_percentage) {
        return coinMarketAdapterV2.getManyCoinsMetadata(vs_currency, ids, category, order, per_page, page, sparkline, price_change_percentage);
    }

    @Override
    public Flux<CoinMarketIntegrationDTO> getAllMarkets(String vs_currency, String ids) {
        return coinMarketAdapterV2.getManyCoinsByIds(vs_currency, ids);
    }

}
