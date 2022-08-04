package com.cryptonita.app.integration.adapters.impl;

import com.cryptonita.app.dto.integration.CoinMetadataDTO;
import com.cryptonita.app.integration.adapters.ICoinMetaAdapter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
public class CoinMetaAdapterImpl implements ICoinMetaAdapter {

    private static final String COIN_CAP_URL = "api.coincap.io/v2/assets";
    private static final String COIN_MARKET_URL = "https://pro-api.coinmarketcap.com";

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${coinMarketCapApiKey}")
    private String COIN_MARKET_KEY;

    private final WebClient coinCapClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(
                    HttpClient.create().followRedirect(true)
            ))
            .baseUrl(COIN_CAP_URL)
            .build();

    private final WebClient coinMarketClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(
                    HttpClient.create().followRedirect(true)
            ))
            .baseUrl(COIN_MARKET_URL)
            .build();


    @Override
    public Mono<CoinMetadataDTO> getCoinMetadataBySymbol(String symbol) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Mono<CoinMetadataDTO> getCoinMetadataByName(String name) {
        Mono<String> coinCapMono = coinCapClient.get()
                .uri(String.format("/%s", name))
                .retrieve()
                .bodyToMono(String.class);

        Mono<String> marketCapMono = coinMarketClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/v2/cryptocurrency/info")
                                .queryParam("slug", name)
                                .build()
                )
                .header("X-CMC_PRO_API_KEY", COIN_MARKET_KEY)
                .retrieve()
                .bodyToMono(String.class);

        return Flux.zip(coinCapMono, marketCapMono)
                .single()
                .map(this::mapToJson);
    }

    @Override
    public Mono<CoinMetadataDTO> getCoinMetadata(int rank) {
        throw new UnsupportedOperationException();
    }

    @SneakyThrows
    private CoinMetadataDTO mapToJson(Tuple2<String, String> tuple) {
        CoinMetadataDTO coinCapDTO = mapCoinCapToJson(tuple.getT1());
        CoinMetadataDTO marketCapDTO = mapMarketCapToJson(tuple.getT2());

        coinCapDTO.description = marketCapDTO.description;
        coinCapDTO.logo = marketCapDTO.logo;

        return coinCapDTO;
    }

    @SneakyThrows
    private CoinMetadataDTO mapCoinCapToJson(String s) {
        JsonNode json = objectMapper.readTree(s);
        JsonNode data = json.get("data");

        return CoinMetadataDTO.builder()
                .name(data.get("id").asText())
                .symbol(data.get("symbol").asText())
                .supply(data.get("supply").asDouble())
                .maxSupply(data.get("maxSupply").asDouble())
                .marketCapUsd(data.get("marketCapUsd").asDouble())
                .volumeUsd24Hr(data.get("volumeUsd24Hr").asDouble())
                .priceUsd(data.get("priceUsd").asDouble())
                .changePercent24Hr(data.get("changePercent24Hr").asDouble())
                .vwap24Hr(data.get("vwap24Hr").asDouble())
                .build();
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
