package com.cryptonita.app.integration.adapters.impl;

import com.cryptonita.app.dto.integration.CoinMarketIntegrationDTO;
import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import com.cryptonita.app.integration.adapters.ICoinMarketAdapterV2;
import com.cryptonita.app.integration.adapters.mappers.AdapterMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Service
@AllArgsConstructor
public class CoinMarketAdapterImplV2 implements ICoinMarketAdapterV2 {

    private static final String URL = "https://api.coingecko.com/api/v3/coins/markets";

    private final WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(
                    HttpClient.create().followRedirect(true)
            ))
            .baseUrl(URL)
            .build();

    private final AdapterMapper<CoinMarketIntegrationDTO> mapper;

    @Override
    public Flux<CoinMarketIntegrationDTO> getManyCoins(String vs_currency) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .queryParam("vs_currency",vs_currency)
                                .build()
                )
                .retrieve()
                .bodyToFlux(String.class)
                .flatMap(s -> Flux.fromStream(mapper.mapManyToDto(s).stream()));
    }


    @Override
    public Flux<CoinMarketIntegrationDTO> getManyCoinsMetadata(String vs_currency, String ids, String category, String order, Integer per_page,
                                                               Integer page, Boolean sparkline, String price_change_percentage) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .queryParam("vs_currency",vs_currency)
                                .queryParam("ids",ids)
                                .queryParam("category",category)
                                .queryParam("order",order)
                                .queryParam("per_page",per_page)
                                .queryParam("page",page)
                                .queryParam("sparkline",sparkline)
                                .queryParam("price_change_percentage",price_change_percentage)
                                .build()
                )
                .retrieve()
                .bodyToFlux(String.class)
                .flatMap(s -> Flux.fromStream(mapper.mapManyToDto(s).stream()));
    }

    @Override
    public Flux<CoinMarketIntegrationDTO> getManyCoinsByIds(String vs_currency, String ids) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .queryParam("vs_currency",vs_currency)
                                .queryParam("ids",ids)
                                .build()
                )
                .retrieve()
                .bodyToFlux(String.class)
                .flatMap(s -> Flux.fromStream(mapper.mapManyToDto(s).stream()));
    }
}
