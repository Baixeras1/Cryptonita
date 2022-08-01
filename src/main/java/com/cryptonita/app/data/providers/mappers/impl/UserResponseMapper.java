package com.cryptonita.app.data.providers.mappers.impl;

import com.cryptonita.app.data.entities.UserModel;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.response.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper implements IMapper<UserModel, UserResponseDTO> {

    @Override
    public UserResponseDTO mapToDto(UserModel userModel) {
        return UserResponseDTO.builder()
                .mail(userModel.getMail())
                .username(userModel.getUsername())
                .role(userModel.getRole())
                .type(userModel.getType())
                .build();
    }

    @Override
    public UserModel mapToEntity(UserResponseDTO userResponseDTO) {
        throw new UnsupportedOperationException();
    }
}
