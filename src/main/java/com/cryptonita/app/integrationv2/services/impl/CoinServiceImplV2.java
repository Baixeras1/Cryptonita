package com.cryptonita.app.integrationv2.services.impl;

import com.cryptonita.app.dto.integration.CoinInfoIntegrationDTO;
import com.cryptonita.app.integrationv2.adapters.ICoinInfoAdapterv2;
import com.cryptonita.app.integrationv2.services.ICoinService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CoinServiceImplV2 implements ICoinService {

    private final ICoinInfoAdapterv2 infoAdapter;

    @Override
    public Mono<CoinInfoIntegrationDTO> get(String coinID) {
        return infoAdapter.get(coinID);
    }

    @Override
    public Flux<CoinInfoIntegrationDTO> getAll(String... coinIDs) {
        return infoAdapter.get(coinIDs);
    }
}
