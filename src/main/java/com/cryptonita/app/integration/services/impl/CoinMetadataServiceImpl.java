package com.cryptonita.app.integration.services.impl;

import com.cryptonita.app.dto.integration.CoinMetadataDTO;
import com.cryptonita.app.integration.adapters.ICoinMetaAdapter;
import com.cryptonita.app.integration.services.ICoinMetadataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CoinMetadataServiceImpl implements ICoinMetadataService {

    private final ICoinMetaAdapter metaAdapter;

    @Override
    public Mono<CoinMetadataDTO> getCoinMetadataBySymbol(String symbol) {
        return metaAdapter.getCoinMetadataBySymbol(symbol);
    }

    @Override
    public Mono<CoinMetadataDTO> getCoinMetadataByName(String name) {
        return metaAdapter.getCoinMetadataByName(name);
    }

    @Override
    public Mono<CoinMetadataDTO> getCoinMetadataByRank(int rank) {
        return metaAdapter.getCoinMetadata(rank);
    }
}
