package com.cryptonita.app.data.providers;

import com.cryptonita.app.dto.data.response.RegisterResponseDTO;
import com.cryptonita.app.dto.request.RegisterRequestDTO;


import java.time.LocalDate;
import java.util.List;

public interface IRegisterProvider {

    /**
     * Convenient method to get the list of logs from a user
     * @param registerResponseDTO
     */
    RegisterResponseDTO log (RegisterRequestDTO registerResponseDTO);

    List <RegisterRequestDTO> getLogsFromUsers (String user, LocalDate start,LocalDate end);

}
