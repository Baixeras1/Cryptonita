package com.cryptonita.app.integration.adapters.impl;

import com.cryptonita.app.dto.integration.CandleInfoDTO;
import com.cryptonita.app.integration.adapters.ICandleCapMetaAdapter;
import com.cryptonita.app.integration.adapters.mappers.AdapterMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClient;

@Service
@AllArgsConstructor
public class CandleCapMetaAdapterImpl implements ICandleCapMetaAdapter {

    private final String URL = "api.coincap.io/v2";

    private final WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(
                    HttpClient.create().followRedirect(true)
            ))
            .baseUrl(URL)
            .build();

    private final AdapterMapper<CandleInfoDTO> mapper;

    @Override
    public Flux<CandleInfoDTO> getCandleOfCoin(String exchange, String interval, String baseId, String quoteId, Long start, Long end) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/candles")
                                .queryParam("exchange", exchange)
                                .queryParam("interval", interval)
                                .queryParam("baseId", baseId)
                                .queryParam("quoteId", quoteId)
                                .queryParam("start", start)
                                .queryParam("end", end)
                                .build()
                )
                .retrieve()
                .bodyToFlux(String.class)
                .flatMap(s -> Flux.fromStream(mapper.mapManyToDto(s).stream()));
    }

    @Override
    public Flux<CandleInfoDTO> getCandleOfCoin(String exchange, String interval, String baseId, String quoteId) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/candles")
                                .queryParam("exchange", exchange)
                                .queryParam("interval", interval)
                                .queryParam("baseId", baseId)
                                .queryParam("quoteId", quoteId)
                                .build()
                )
                .retrieve()
                .bodyToFlux(String.class)
                .flatMap(s -> Flux.fromStream(mapper.mapManyToDto(s).stream()));
    }

}
