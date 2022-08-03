package com.cryptonita.app.integration.adapters.impl;

import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import com.cryptonita.app.integration.adapters.IHistoryAdapter;
import com.cryptonita.app.integration.adapters.mappers.AdapterMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClient;

@Service
@AllArgsConstructor
public class HistoryAdapterCoinCap implements IHistoryAdapter {

    private static final String URL = "api.coincap.io/v2/assets";

    private final WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(
                    HttpClient.create().followRedirect(true)
            ))
            .baseUrl(URL)
            .build();

    private final AdapterMapper<HistoryInfoDTO> mapper;

    @Override
    public Flux<HistoryInfoDTO> getHistoryOfCoin(String symbol, String interval, Long start, Long end) {
        return webClient.get()
                .uri(String.format("/%s/history", symbol, interval, start, end))
                .attribute("interval", interval)
                .attribute("start", start)
                .attribute("end", end)
                .retrieve()
                .bodyToFlux(String.class)
                .flatMap(s -> Flux.fromStream(mapper.mapManyToDto(s).stream()));
    }

    @Override
    public Flux<HistoryInfoDTO> getHistoryOfCoin(String symbol, String interval) {
        return webClient.get()
                .uri(String.format("/%s/history", symbol))
                .attribute("interval", interval)
                .retrieve()
                .bodyToFlux(String.class)
                .flatMap(s -> Flux.fromStream(mapper.mapManyToDto(s).stream()));

    }
}
