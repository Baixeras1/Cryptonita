package com.cryptonita.app.data.providers.mappers.impl;

import com.cryptonita.app.data.entities.CoinModel;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.response.CoinResponseDTO;

public class CoinMapper implements IMapper<CoinModel, CoinResponseDTO> {

    @Override
    public CoinResponseDTO mapToDto(CoinModel coinModel) {
       return CoinResponseDTO.builder()
               .id(coinModel.getId())
               .name(coinModel.getName())
               .build();
    }

    @Override
    public CoinModel mapToEntity(CoinResponseDTO coinResponseDTO) {
        throw new UnsupportedOperationException();
    }
}
