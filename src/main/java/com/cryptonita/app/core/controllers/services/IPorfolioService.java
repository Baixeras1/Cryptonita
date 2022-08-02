package com.cryptonita.app.core.controllers.services;

import com.cryptonita.app.dto.response.UserResponseDTO;

import java.util.List;

public interface IPorfolioService {

    UserResponseDTO getPortfolio(String user);

    List<UserResponseDTO> getAll();

}
