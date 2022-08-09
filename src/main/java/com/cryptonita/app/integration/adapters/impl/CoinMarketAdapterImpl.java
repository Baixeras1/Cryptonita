package com.cryptonita.app.integration.adapters.impl;

import com.cryptonita.app.dto.integration.CoinMarketDTO;
import com.cryptonita.app.integration.adapters.ICoinMarketAdapter;
import com.cryptonita.app.integration.adapters.mappers.CoinMarketMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Component
public class CoinMarketAdapterImpl implements ICoinMarketAdapter {

    private static final String COIN_CAP_URL = "https://api.coingecko.com/api/v3/coins/markets";

    @Autowired
    private CoinMarketMapper mapper;

    private final WebClient coinCapClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(
                    HttpClient.create().followRedirect(true)
            ))
            .baseUrl(COIN_CAP_URL)
            .build();


    @Override
    public Mono<CoinMarketDTO> getCoinMetadataBySymbol(String symbol) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Mono<CoinMarketDTO> getCoinMetadataByName(String name) {
        return coinCapClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .queryParam("vs_currency", "usd")
                                .queryParam("ids", name)
                                .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .map(mapper::mapToDto);
    }

    @Override
    public Mono<CoinMarketDTO> getCoinMetadata(int rank) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Flux<CoinMarketDTO> getManyCoinsMetadata(String manyDTOs) {
        return coinCapClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .queryParam("vs_currency", "usd")
                                .queryParam("ids", manyDTOs)
                                .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .map(mapper::mapManyToDto)
                .flatMapMany(Flux::fromIterable);
    }



}
