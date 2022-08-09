package com.cryptonita.app.data.providers;

import com.cryptonita.app.dto.data.response.RegisterResponseDTO;
import com.cryptonita.app.dto.data.request.RegisterRequestDTO;


import java.time.LocalDate;
import java.util.List;

public interface IRegisterProvider {

    default RegisterResponseDTO log(RegisterRequestDTO dto) {
        return log(dto.user, dto.date, dto.origin, dto.destiny, dto.quantity);
    }

    RegisterResponseDTO log(String username, LocalDate date, String origin, String destiny, double quantity);

    List<RegisterResponseDTO> getLogsFromUsers(String user, LocalDate start, LocalDate end);

    RegisterResponseDTO getOneRegister(long id);

}
