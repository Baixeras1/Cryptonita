package com.cryptonita.app.integration.adapters.mappers;

import com.cryptonita.app.dto.integration.CoinInfoDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.h2.util.json.JSONArray;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*{
        "data": {
        "id": "bitcoin",
        "rank": "1",
        "symbol": "BTC",
        "name": "Bitcoin",
        "supply": "17193925.0000000000000000",
        "maxSupply": "21000000.0000000000000000",
        "marketCapUsd": "119179791817.6740161068269075",
        "volumeUsd24Hr": "2928356777.6066665425687196",
        "priceUsd": "6931.5058555666618359",
        "changePercent24Hr": "-0.8101417214350335",
        "vwap24Hr": "7175.0663247679233209"
        },
        "timestamp": 1533581098863
        }
*/

@Slf4j
@Component
@AllArgsConstructor
public class CoinCapAdapterMapper implements AdapterMapper<CoinInfoDTO> {

    private final ObjectMapper jsonMapper;

    @SneakyThrows
    @Override
    public CoinInfoDTO mapToDto(String s) {
        JsonNode json = jsonMapper.readTree(s);
        JsonNode data = getData(json);

        return mapToDto(data);
    }

    @SneakyThrows
    @Override
    public List<CoinInfoDTO> mapManyToDto(String s) {
        JsonNode json = jsonMapper.readTree(s);
        ArrayNode data = (ArrayNode) getData(json);

        List<CoinInfoDTO> coins = new ArrayList<>();
        data.forEach(node -> coins.add(mapToDto(node)));

        return coins;
    }

    private JsonNode getData(JsonNode node) {
        return node.get("data");
    }

    private CoinInfoDTO mapToDto(JsonNode node) {
        return CoinInfoDTO.builder()
                .name(node.get("name").asText())
                .symbol(node.get("symbol").asText())
                .rank(node.get("rank").asInt())
                .build();
    }

}
