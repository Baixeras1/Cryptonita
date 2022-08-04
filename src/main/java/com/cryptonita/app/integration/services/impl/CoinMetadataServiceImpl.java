package com.cryptonita.app.integration.services.impl;

import com.cryptonita.app.dto.integration.CoinMetadataDTO;
import com.cryptonita.app.integration.adapters.ICoinCapMetaAdapter;
import com.cryptonita.app.integration.adapters.IMarketCapMetaAdapter;
import com.cryptonita.app.integration.services.ICoinMetadataService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
@AllArgsConstructor
public class CoinMetadataServiceImpl implements ICoinMetadataService {

    private final ICoinCapMetaAdapter coinCapMetaAdapter;
    private final IMarketCapMetaAdapter marketCapMetaAdapter;

    @Override
    public Mono<CoinMetadataDTO> getCoinMetadataBySymbol(String symbol) {
        return coinCapMetaAdapter.getCoinMetadataBySymbol(symbol);
    }

    @Override
    public Mono<CoinMetadataDTO> getCoinMetadataByName(String name) {
        return Flux.zip(
                        coinCapMetaAdapter.getCoinMetadataByName(name),
                        marketCapMetaAdapter.getCoinMetadataByName(name)
                )
                .single()
                .map(this::mapToJson);
    }

    @Override
    public Mono<CoinMetadataDTO> getCoinMetadataByRank(int rank) {
        return coinCapMetaAdapter.getCoinMetadata(rank);
    }

    @SneakyThrows
    private CoinMetadataDTO mapToJson(Tuple2<CoinMetadataDTO, CoinMetadataDTO> tuple) {
        tuple.getT1().description = tuple.getT2().description;
        tuple.getT1().logo = tuple.getT2().logo;

        return tuple.getT1();
    }

}
