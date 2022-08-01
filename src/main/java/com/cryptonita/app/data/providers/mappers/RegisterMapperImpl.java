package com.cryptonita.app.data.providers.mappers;

import com.cryptonita.app.data.entities.HistoryModel;
import com.cryptonita.app.dto.response.RegisterResponseDTO;

public class RegisterMapperImpl implements IMapper <HistoryModel , RegisterResponseDTO> {


    @Override
    public RegisterResponseDTO mapToDto(HistoryModel historyModel) {
        return RegisterResponseDTO.builder()
                .user(historyModel.getUser().getUsername())
                .date(historyModel.getDate())
                .origin(historyModel.getOrigin())
                .destiny(historyModel.getDestiny())
                .quantity(historyModel.getQuantity())
                .build();
    }

    @Override
    public HistoryModel mapToEntity(RegisterResponseDTO registerResponseDTO) {
        throw new UnsupportedOperationException();
    }
}
