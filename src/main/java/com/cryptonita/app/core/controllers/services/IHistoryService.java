package com.cryptonita.app.core.controllers.services;

import com.cryptonita.app.dto.request.RegisterRequestDTO;

import java.time.LocalDate;
import java.util.List;

public interface IHistoryService {

    List<RegisterRequestDTO> getAllRegisterUser(String username,LocalDate start,LocalDate end);

    List<RegisterRequestDTO> getOneRegister(long id);
}
