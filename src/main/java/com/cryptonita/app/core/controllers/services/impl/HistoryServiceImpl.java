package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IHistoryService;
import com.cryptonita.app.data.providers.IRegisterProvider;
import com.cryptonita.app.dto.data.response.RegisterResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class HistoryServiceImpl implements IHistoryService {

    private final IRegisterProvider registerProvider;

    @Override
    public List<RegisterResponseDTO> getAllRegisterUser(String username, LocalDate start, LocalDate end) {
        return
                registerProvider.getLogsFromUsers(username,start,end);
    }

    @Override
    public RegisterResponseDTO getOneRegister(long id) {
        return registerProvider.getOneRegister(id);
    }
}
