package com.cryptonita.app.data.providers;

import com.cryptonita.app.dto.response.RegisterResponseDTO;

import java.util.List;

public interface IRegisterProvider {

    /**
     * Convenient method to get the list of logs from a user
     * @param registerResponseDTO
     */
    void log (RegisterResponseDTO registerResponseDTO);

    List <RegisterResponseDTO> getLogsFromUsers (String user);

}
