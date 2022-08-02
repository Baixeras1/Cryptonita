package com.cryptonita.app.data.providers.impl;


import com.cryptonita.app.data.daos.IHistoryDao;
import com.cryptonita.app.data.entities.HistoryModel;
import com.cryptonita.app.data.entities.UserModel;
import com.cryptonita.app.data.providers.IRegisterProvider;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.request.UserRegisterDTO;
import com.cryptonita.app.dto.response.RegisterResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegisterProviderImp implements IRegisterProvider {

    private IHistoryDao historyDao;

    private IMapper<HistoryModel, RegisterResponseDTO> iMapper;
    @Override
    public void log(RegisterResponseDTO registerResponseDTO) {

        HistoryModel model = iMapper.mapToEntity(registerResponseDTO);

        historyDao.save(model);



    }

    @Override
    public List<RegisterResponseDTO> getLogsFromUsers(String user) {
        return historyDao.findAllByUser_Username(user)
                .stream()
                .map(iMapper::mapToDto)
                .collect(Collectors.toList());
    }


}
