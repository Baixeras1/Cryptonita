package com.cryptonita.app.core.controllers.mapper;

import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.controller.CoinDto;
import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import com.cryptonita.app.dto.integration.CoinMarketDTO;
import com.cryptonita.app.dto.integration.CoinMarketIntegrationDTO;
import com.cryptonita.app.integration.services.ICoinIntegrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CoinWithMetaMapper implements IMapper<CoinResponseDTO, Mono<CoinDto>> {

    private final ICoinIntegrationService coinService;

    @Override
    public Mono<CoinDto> mapToDto(CoinResponseDTO coinResponseDTO) {
        return coinService.getAllMarketByIds(coinResponseDTO.coinID)
                .single()
                .map(coinMetadataDTO -> mergeCoinAndMetadata(coinResponseDTO, coinMetadataDTO));
    }

    @Override
    public CoinResponseDTO mapToEntity(Mono<CoinDto> coinDtoMono) {
        throw new UnsupportedOperationException();
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
