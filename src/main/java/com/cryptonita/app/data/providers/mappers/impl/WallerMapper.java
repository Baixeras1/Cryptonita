package com.cryptonita.app.data.providers.mappers.impl;

import com.cryptonita.app.data.entities.WalletModel;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.data.response.WallerResponseDto;
import org.springframework.stereotype.Component;

@Component
public class WallerMapper implements IMapper<WalletModel, WallerResponseDto> {

    @Override
    public WallerResponseDto mapToDto(WalletModel walletModel) {
        return WallerResponseDto.builder()
                .id(walletModel.getId())
                .coinName(walletModel.getCoin().getName())
                .quality(walletModel.getQuantity())
                .build();
    }

    @Override
    public WalletModel mapToEntity(WallerResponseDto wallerResponseDto) {
        throw new RuntimeException();
    }
}
