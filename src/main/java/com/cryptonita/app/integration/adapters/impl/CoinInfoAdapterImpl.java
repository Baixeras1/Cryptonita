package com.cryptonita.app.integration.adapters.impl;

import com.cryptonita.app.dto.integration.CoinInfoIntegrationDTO;
import com.cryptonita.app.integration.adapters.ICoinInfoAdapter;
import com.cryptonita.app.integration.adapters.mappers.AdapterMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CoinInfoAdapterImpl implements ICoinInfoAdapter {

    private static final WebClient webclient = WebClient.builder()
            .baseUrl("https://api.coingecko.com/api/v3/coins/")
            .build();

    private final AdapterMapper<CoinInfoIntegrationDTO> mapper;

    @Override
    public Mono<CoinInfoIntegrationDTO> get(String coinID, CoinInfoOptions options) {
        return webclient.get()
                .uri(builder -> {
                    builder.path(coinID);
                    return options.queryParams(builder);
                })
                .retrieve()
                .bodyToMono(String.class)
                .map(mapper::mapToDto);
    }

}
