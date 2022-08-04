package com.cryptonita.app.integration.adapters.mappers;

import com.cryptonita.app.dto.integration.CoinMetadataDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CoinCapMetaMapper implements AdapterMapper<CoinMetadataDTO> {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public CoinMetadataDTO mapToDto(String s) {
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

    @Override
    public List<CoinMetadataDTO> mapManyToDto(String s) {
        throw new UnsupportedOperationException();
    }

}
