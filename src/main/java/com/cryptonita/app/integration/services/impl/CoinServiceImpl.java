package com.cryptonita.app.integration.services.impl;

import com.cryptonita.app.dto.integration.CoinInfoDTO;
import com.cryptonita.app.integration.adapters.ICoinInfoAdapter;
import com.cryptonita.app.integration.services.ICoinInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CoinServiceImpl implements ICoinInfoService {

    private final ICoinInfoAdapter adapter;

    @Override
    public Flux<CoinInfoDTO> getAll() {
        return adapter.getAll();
    }

    @Override
    public Mono<CoinInfoDTO> getBySymbol(String symbol) {
        return adapter.getBySymbol(symbol);
    }

    @Override
    public Mono<CoinInfoDTO> getByName(String name) {
        return adapter.getByName(name);
    }

    @Override
    public Mono<CoinInfoDTO> getByRank(int rank) {
        return adapter.getByRank(rank);
    }

}
