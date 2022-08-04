package com.cryptonita.app.data.providers.impl;

import com.cryptonita.app.data.daos.IHistoryDao;
import com.cryptonita.app.data.daos.IUserDao;
import com.cryptonita.app.data.entities.HistoryModel;
import com.cryptonita.app.data.entities.UserModel;
import com.cryptonita.app.data.providers.IRegisterProvider;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.data.response.RegisterResponseDTO;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import com.cryptonita.app.dto.request.RegisterRequestDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegisterProviderImp implements IRegisterProvider {

    private IHistoryDao historyDao;
    private IUserDao userDao;

    private IMapper<HistoryModel, RegisterRequestDTO> iMapper;
    private IMapper<UserModel, UserResponseDTO> userResponseDTOIMapper;
    @Override
    public RegisterResponseDTO log(RegisterRequestDTO registerRequestDTO) {

        UserModel userModel = userDao.findByUsername(registerRequestDTO.getUser()).orElse(null);
        if(userModel == null)
            throw new RuntimeException("Ese usuario no existe en la BBDD");

        HistoryModel model = iMapper.mapToEntity(registerRequestDTO);
        historyDao.save(model);

        return RegisterResponseDTO.builder()
                .user(userResponseDTOIMapper.mapToDto(userModel))
                .destiny(model.getDestiny())
                .origin(model.getOrigin())
                .date(model.getDate())
                .quantity(model.getQuantity())
                .build();
    }

    @Override
    public List<RegisterRequestDTO> getLogsFromUsers(String user, LocalDate start, LocalDate end) {

        UserModel userModel = userDao.findByUsername(user).orElse(null);
        if(userModel == null)
            throw new RuntimeException("Usuario no existe");

        return historyDao.findAllByUser_UsernameAndDateAfterAndDateBefore(user,start,end).stream()
                .map(iMapper::mapToDto)
                .collect(Collectors.toList());
    }



}
