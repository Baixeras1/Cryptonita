package com.cryptonita.app.data.providers.mappers.impl;

import com.cryptonita.app.data.entities.HistoryModel;
import com.cryptonita.app.data.entities.UserModel;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.request.RegisterRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class RegisterRequestMapper implements IMapper<HistoryModel, RegisterRequestDTO> {
    @Override
    public RegisterRequestDTO mapToDto(HistoryModel historyModel) {
        return null;
    }

    @Override
    public HistoryModel mapToEntity(RegisterRequestDTO registerResponseDTO) {
        return HistoryModel.builder()
                //.user(registerResponseDTO.getUser())
                .date(registerResponseDTO.getDate())
                .origin(registerResponseDTO.getOrigin())
                .destiny(registerResponseDTO.getDestiny())
                .quantity(registerResponseDTO.getQuantity())
                .build();
    }
}
