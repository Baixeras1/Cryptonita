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
public class MarketCapMetaMapper implements AdapterMapper<CoinMetadataDTO> {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public CoinMetadataDTO mapToDto(String s) {
        JsonNode json = objectMapper.readTree(s);
        JsonNode data = json.get("data");

        JsonNode body = data.fields().next().getValue();

        return CoinMetadataDTO.builder()
                .logo(body.get("logo").asText())
                .description(body.get("description").asText())
                .build();
    }

    @Override
    public List<CoinMetadataDTO> mapManyToDto(String s) {
        throw new UnsupportedOperationException();
    }
}
