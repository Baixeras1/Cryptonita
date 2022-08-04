package com.cryptonita.app.integration.adapters.impl;

import com.cryptonita.app.dto.integration.CoinMetadataDTO;
import com.cryptonita.app.integration.adapters.ICoinCapMetaAdapter;
import com.cryptonita.app.integration.adapters.mappers.CoinCapMetaMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.function.Tuple2;

@Slf4j
@Component
public class CoinCapMetaAdapterImpl implements ICoinCapMetaAdapter {

    private static final String COIN_CAP_URL = "api.coincap.io/v2/assets";

    @Autowired
    private CoinCapMetaMapper mapper;


    private final WebClient coinCapClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(
                    HttpClient.create().followRedirect(true)
            ))
            .baseUrl(COIN_CAP_URL)
            .build();


    @Override
    public Mono<CoinMetadataDTO> getCoinMetadataBySymbol(String symbol) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Mono<CoinMetadataDTO> getCoinMetadataByName(String name) {
        return coinCapClient.get()
                .uri(String.format("/%s", name))
                .retrieve()
                .bodyToMono(String.class)
                .map(mapper::mapToDto);
    }

    @Override
    public Mono<CoinMetadataDTO> getCoinMetadata(int rank) {
        throw new UnsupportedOperationException();
    }

}
