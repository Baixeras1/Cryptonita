package com.cryptonita.app.core.controllers.mapper;

import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.controller.CoinDto;
import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import com.cryptonita.app.dto.integration.CoinMarketDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CoinWithMetaMapper implements IMapper<CoinResponseDTO, Mono<CoinDto>> {

    private final ICoinMarketService metadataService;

    @Override
    public Mono<CoinDto> mapToDto(CoinResponseDTO coinResponseDTO) {
        return metadataService.getCoinMetadataByName(coinResponseDTO.name)
                .map(coinMetadataDTO -> mergeCoinAndMetadata(coinResponseDTO, coinMetadataDTO));
    }

    @Override
    public CoinResponseDTO mapToEntity(Mono<CoinDto> coinDtoMono) {
        throw new UnsupportedOperationException();
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
