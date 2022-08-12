package com.cryptonita.app.integration.adapters.mappers;

import com.cryptonita.app.dto.integration.CoinMarketDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CoinMarketMapper implements AdapterMapper<CoinMarketDTO> {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public CoinMarketDTO mapToDto(String s) {
        ArrayNode json = (ArrayNode) objectMapper.readTree(s);
        System.out.println(json);
        JsonNode data = json.iterator().next();

        return innerMap(data);
    }

    @SneakyThrows
    @Override
    public List<CoinMarketDTO> mapManyToDto(String s) {
        ArrayNode json = (ArrayNode) objectMapper.readTree(s);
        List<CoinMarketDTO> list = new ArrayList<>();

        for (JsonNode jsonNode : json) {
            list.add(innerMap(jsonNode));
        }

        return list;
    }

    private CoinMarketDTO innerMap(JsonNode data) {
        return CoinMarketDTO.builder()
                .name(data.get("id").asText())
                .symbol(data.get("symbol").asText())
                .logo(data.get("image").asText())
                .priceUsd(data.get("current_price").asDouble())
                .supply(data.get("circulating_supply").asDouble())
                .maxSupply(data.get("total_supply").asDouble())
                .marketCapUsd(data.get("market_cap").asDouble())
                .totalVolume(data.get("total_volume").asDouble())
                .changePercent24Hr(data.get("price_change_percentage_24h").asDouble())
                .build();
    }

}
