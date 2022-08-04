package com.cryptonita.app.integration.adapters.mappers;

import com.cryptonita.app.dto.integration.CandleInfoDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class CandleCapMetaMapper implements AdapterMapper<CandleInfoDTO>{

    private final ObjectMapper jsonMapper;

    @SneakyThrows
    @Override
    public CandleInfoDTO mapToDto(String s) {
        JsonNode jsonNode = jsonMapper.readTree(s);
        JsonNode data = jsonNode.get("data");

        return mapper(data);
    }

    @SneakyThrows
    @Override
    public List<CandleInfoDTO> mapManyToDto(String s) {
        JsonNode jsonNode = jsonMapper.readTree(s);
        ArrayNode data = (ArrayNode) jsonNode.get("data");

        List<CandleInfoDTO> candles = new ArrayList<>();
        data.forEach(node -> candles.add(mapper(node)));

        return candles;
    }

    private CandleInfoDTO mapper(JsonNode jsonNode) {
        return CandleInfoDTO.builder()
                .open(jsonNode.get("open").asDouble())
                .high(jsonNode.get("high").asDouble())
                .low(jsonNode.get("low").asDouble())
                .close(jsonNode.get("close").asDouble())
                .volume(jsonNode.get("volume").asDouble())
                .period(jsonNode.get("period").asDouble())
                .build();
    }
}
