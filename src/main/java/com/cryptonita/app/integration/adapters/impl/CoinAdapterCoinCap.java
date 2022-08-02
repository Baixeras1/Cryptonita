package com.cryptonita.app.integration.adapters.impl;

import com.cryptonita.app.dto.integration.CoinInfoDTO;
import com.cryptonita.app.integration.adapters.ICoinAdapter;
import com.cryptonita.app.integration.adapters.mappers.AdapterMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Service
@AllArgsConstructor
public class CoinAdapterCoinCap implements ICoinAdapter {

    private static final String URL = "api.coincap.io/v2/assets";

    private final WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(
                    HttpClient.create().followRedirect(true)
            ))
            .baseUrl(URL)
            .build();

    private final AdapterMapper<CoinInfoDTO> mapper;

    @Override
    public Flux<CoinInfoDTO> getAll() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(String.class)
                .flatMap(s -> Flux.fromStream(mapper.mapManyToDto(s).stream()));
    }

    @Override
    public Mono<CoinInfoDTO> getBySymbol(String symbol) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Mono<CoinInfoDTO> getByName(String symbol) {
        return webClient.get()
                .uri("/" + symbol)
                .retrieve()
                .bodyToMono(String.class)
                .map(mapper::mapToDto);
    }

    @Override
    public Mono<CoinInfoDTO> getByRank(int rank) {
        throw new UnsupportedOperationException();
    }
}
