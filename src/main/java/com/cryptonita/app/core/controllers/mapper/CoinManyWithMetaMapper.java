package com.cryptonita.app.core.controllers.mapper;

import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.controller.CoinDto;
import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import com.cryptonita.app.dto.integration.CoinMarketDTO;
import com.cryptonita.app.dto.integration.CoinMarketIntegrationDTO;
import com.cryptonita.app.integration.services.ICoinIntegrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CoinManyWithMetaMapper implements IMapper<List<CoinResponseDTO>, Flux<CoinDto>> {

    private final ICoinIntegrationService coinService;

    @Override
    public Flux<CoinDto> mapToDto(List<CoinResponseDTO> coinResponseDTOS) {
        String coins = coinResponseDTOS.stream()
                .map(responseDTO -> responseDTO.coinID)
                .collect(Collectors.joining(","));

        Map<String, CoinResponseDTO> map = mapCoins(coinResponseDTOS);

        return coinService.getAllMarketByIds(coins)
                .map(coinMarketDTO -> mergeCoinAndMetadata(map.get(coinMarketDTO.id), coinMarketDTO));
    }

    @Override
    public List<CoinResponseDTO> mapToEntity(Flux<CoinDto> coinDtoFlux) {
        throw new UnsupportedOperationException();
    }

    private Map<String, CoinResponseDTO> mapCoins(List<CoinResponseDTO> coinResponseDTOS) {
        Map<String, CoinResponseDTO> map = new HashMap<>();
        coinResponseDTOS.forEach(dto -> map.put(dto.coinID, dto));

        return map;
    }

    private CoinDto mergeCoinAndMetadata(CoinResponseDTO responseDTO, CoinMarketIntegrationDTO marketDTO) {
        return CoinDto.builder()
                .id(responseDTO.coinID)
                .name(responseDTO.name)
                .symbol(responseDTO.symbol)
                .rank(responseDTO.id)
                .marketData(marketDTO)
                .build();
    }

}
