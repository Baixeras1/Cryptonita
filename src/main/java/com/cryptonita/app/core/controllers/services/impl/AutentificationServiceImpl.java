package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IAutentificationService;
import com.cryptonita.app.data.providers.impl.UserProviderImpl;
import com.cryptonita.app.dto.data.request.UserRegisterDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutentificationServiceImpl implements IAutentificationService {


    UserProviderImpl userProvider;

    @Override
    public UserRegisterDTO register(UserRegisterDTO userRegisterDTO) {

        UserRegisterDTO newUser = UserRegisterDTO.builder()
                .mail(userRegisterDTO.mail)
                .password(userRegisterDTO.password)
                .role(userRegisterDTO.role)
                .type(userRegisterDTO.type)
                .username(userRegisterDTO.username)
                .build();

        userProvider.register(newUser);

        return newUser;
    }
}
