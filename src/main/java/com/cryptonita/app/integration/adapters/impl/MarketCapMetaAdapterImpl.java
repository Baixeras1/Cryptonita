package com.cryptonita.app.integration.adapters.impl;

import com.cryptonita.app.dto.integration.CoinMetadataDTO;
import com.cryptonita.app.integration.adapters.IMarketCapMetaAdapter;
import com.cryptonita.app.integration.adapters.mappers.MarketCapMetaMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Component
public class MarketCapMetaAdapterImpl implements IMarketCapMetaAdapter {

    private static final String COIN_MARKET_URL = "https://pro-api.coinmarketcap.com";

    @Value("${coinMarketCapApiKey}")
    private String COIN_MARKET_KEY;

    private final WebClient coinMarketClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(
                    HttpClient.create().followRedirect(true)
            ))
            .baseUrl(COIN_MARKET_URL)
            .build();

    @Autowired
    private MarketCapMetaMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<CoinMetadataDTO> getCoinMetadataBySymbol(String symbol) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Mono<CoinMetadataDTO> getCoinMetadataByName(String name) {
        return coinMarketClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/v2/cryptocurrency/info")
                                .queryParam("slug", name)
                                .build()
                )
                .header("X-CMC_PRO_API_KEY", COIN_MARKET_KEY)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::mapMarketCapToJson);
    }

    @Override
    public Mono<CoinMetadataDTO> getCoinMetadata(int rank) {
        throw new UnsupportedOperationException();
    }

    @SneakyThrows
    private CoinMetadataDTO mapMarketCapToJson(String s) {
        JsonNode json = objectMapper.readTree(s);
        JsonNode data = json.get("data");

        JsonNode body = data.fields().next().getValue();

        return CoinMetadataDTO.builder()
                .logo(body.get("logo").asText())
                .description(body.get("description").asText())
                .build();
    }

}
