package com.cryptonita.app.data.providers.mappers.impl;

import com.cryptonita.app.data.entities.StackingModel;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.response.StackingDTO;

public class StackingResponseMapper implements IMapper<StackingModel, StackingDTO> {
    @Override
    public StackingDTO mapToDto(StackingModel stackingModel) {
        return StackingDTO.builder()
                .userName(stackingModel.getUser().getUsername())
                .coinName(stackingModel.getCoin().getName())
                .quantity(stackingModel.getQuantity())
                .createdAt(stackingModel.getCreatedAt())
                .daysToExpire(stackingModel.getDaysToExpire())
                .build();
    }

    @Override
    public StackingModel mapToEntity(StackingDTO stackingDTO) {throw new UnsupportedOperationException();}
}
