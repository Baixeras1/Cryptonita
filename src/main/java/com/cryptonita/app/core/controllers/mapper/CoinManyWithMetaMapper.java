package com.cryptonita.app.core.controllers.mapper;

import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.controller.CoinDto;
import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import com.cryptonita.app.dto.integration.CoinMarketDTO;
import com.cryptonita.app.integration.services.ICoinMarketService;
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

    private final ICoinMarketService metadataService;

    @Override
    public Flux<CoinDto> mapToDto(List<CoinResponseDTO> coinResponseDTOS) {
        String coins = coinResponseDTOS.stream()
                .map(responseDTO -> responseDTO.name)
                .collect(Collectors.joining(","));

        Map<String, CoinResponseDTO> map = mapCoins(coinResponseDTOS);

        return metadataService.getManyCoinsMeta(coins)
                .map(coinMarketDTO -> mergeCoinAndMetadata(map.get(coinMarketDTO.name), coinMarketDTO));
    }

    @Override
    public List<CoinResponseDTO> mapToEntity(Flux<CoinDto> coinDtoFlux) {
        throw new UnsupportedOperationException();
    }

    private Map<String, CoinResponseDTO> mapCoins(List<CoinResponseDTO> coinResponseDTOS) {
        Map<String, CoinResponseDTO> map = new HashMap<>();
        coinResponseDTOS.forEach(dto -> map.put(dto.name, dto));

        return map;
    }

    private CoinDto mergeCoinAndMetadata(CoinResponseDTO responseDTO, CoinMarketDTO metadataDTO) {
        return CoinDto.builder()
                .id(responseDTO.id)
                .symbol(responseDTO.symbol)
                .name(responseDTO.name)
                .logo(metadataDTO.logo)
                .rank(responseDTO.rank)
                .supply(metadataDTO.supply)
                .maxSupply(metadataDTO.maxSupply)
                .marketCapUsd(metadataDTO.marketCapUsd)
                .totalVolumen(metadataDTO.totalVolume)
                .priceUsd(metadataDTO.priceUsd)
                .changePercent24Hr(metadataDTO.changePercent24Hr)
                .build();
    }

}
