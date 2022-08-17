package com.cryptonita.app.integrationv2.adapters;

import com.cryptonita.app.dto.integration.CoinInfoIntegrationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.SneakyThrows;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.net.URI;

public interface ICoinInfoAdapterv2 {

    default Mono<CoinInfoIntegrationDTO> get(String coinID) {
        return get(coinID, CoinInfoOptions.builder().build());
    }

    Mono<CoinInfoIntegrationDTO> get(String coinID, CoinInfoOptions options);

    default Flux<CoinInfoIntegrationDTO> get(String... coinIDs) {
        return get(CoinInfoOptions.builder().build(), coinIDs);
    }

    default Flux<CoinInfoIntegrationDTO> get(CoinInfoOptions option, String... coinIDs) {
        return Flux.fromArray(coinIDs).flatMap(s -> get(s, option));
    }

    @AllArgsConstructor
    @Builder
    final class CoinInfoOptions {

        public final boolean localization;
        public final boolean tickers;
        public final boolean market_data;
        public final boolean community_data;
        public final boolean sparkline;
        public final boolean developer_data;

        @SneakyThrows
        public URI queryParams(UriBuilder builder) {
            for (Field declaredField : CoinInfoOptions.class.getDeclaredFields()) {
                builder.queryParam(declaredField.getName(), declaredField.get(this));
            }

            return builder.build();
        }

    }

}
