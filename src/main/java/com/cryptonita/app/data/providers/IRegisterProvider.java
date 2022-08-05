package com.cryptonita.app.data.providers;

import com.cryptonita.app.dto.data.response.RegisterResponseDTO;
import com.cryptonita.app.dto.request.RegisterRequestDTO;


import java.time.LocalDate;
import java.util.List;

public interface IRegisterProvider {


    RegisterResponseDTO log (String username,LocalDate date, String origin,String destiny,double quantity);

    List <RegisterResponseDTO> getLogsFromUsers (String user, LocalDate start,LocalDate end);

    RegisterResponseDTO getOneRegister(long id);

}
