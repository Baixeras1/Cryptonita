package com.cryptonita.app.data.providers.mappers.impl;

import com.cryptonita.app.data.entities.HistoryModel;
import com.cryptonita.app.data.entities.UserModel;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.data.response.RegisterResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class RegisterRequestMapper implements IMapper<HistoryModel, RegisterResponseDTO> {
    @Override
    public RegisterResponseDTO mapToDto(HistoryModel historyModel) {
        return null;
    }

    @Override
    public HistoryModel mapToEntity(RegisterResponseDTO registerResponseDTO) {
        UserModel model = UserModel.builder()
                .username(registerResponseDTO.getUser())
                .build();

        return HistoryModel.builder()
                .user(model)
                .date(registerResponseDTO.getDate())
                .origin(registerResponseDTO.getOrigin())
                .destiny(registerResponseDTO.getDestiny())
                .quantity(registerResponseDTO.getQuantity())
                .build();
    }
}
