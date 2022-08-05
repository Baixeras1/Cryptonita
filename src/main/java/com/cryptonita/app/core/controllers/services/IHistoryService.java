package com.cryptonita.app.core.controllers.services;

import com.cryptonita.app.dto.data.response.RegisterResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface IHistoryService {

    List<RegisterResponseDTO> getAllRegisterUser(String username, LocalDate start, LocalDate end);

    RegisterResponseDTO getOneRegister(long id);
}
