package com.cryptonita.app.core.controllers.services;

import com.cryptonita.app.dto.data.request.UserRegisterDTO;
import com.cryptonita.app.dto.data.response.UserResponseDTO;

public interface IAutentificationService {

    public UserResponseDTO register(UserRegisterDTO userRegisterDTO);

}
