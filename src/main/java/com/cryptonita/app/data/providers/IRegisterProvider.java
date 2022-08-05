package com.cryptonita.app.data.providers;

import com.cryptonita.app.dto.data.response.RegisterResponseDTO;
import com.cryptonita.app.dto.request.RegisterRequestDTO;


import java.time.LocalDate;
import java.util.List;

public interface IRegisterProvider {

    /**
     * Convenient method to get the list of logs from a user
     * @param registerRequestDTO
     */
    RegisterResponseDTO log (RegisterRequestDTO registerRequestDTO);

    List <RegisterResponseDTO> getLogsFromUsers (String user, LocalDate start,LocalDate end);

    RegisterResponseDTO getOneRegister(long id);

}
