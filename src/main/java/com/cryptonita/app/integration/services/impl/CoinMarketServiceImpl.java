package com.cryptonita.app.integration.services.impl;

import com.cryptonita.app.dto.integration.CoinMarketDTO;
import com.cryptonita.app.integration.adapters.ICoinMarketAdapter;
import com.cryptonita.app.integration.services.ICoinMarketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CoinMarketServiceImpl implements ICoinMarketService {

    private final ICoinMarketAdapter coinMarketAdapter;

    @Override
    public Mono<CoinMarketDTO> getCoinMetadataBySymbol(String symbol) {
        return coinMarketAdapter.getCoinMetadataBySymbol(symbol);
    }

    @Override
    public Mono<CoinMarketDTO> getCoinMetadataByName(String name) {
        return coinMarketAdapter.getCoinMetadataByName(name);
    }

    @Override
    public Mono<CoinMarketDTO> getCoinMetadataByRank(int rank) {
        return coinMarketAdapter.getCoinMetadata(rank);
    }

    @Override
    public Flux<CoinMarketDTO> getManyCoinsMeta(String manyCoins) {
        return coinMarketAdapter.getManyCoinsMetadata(manyCoins);
    }

}
