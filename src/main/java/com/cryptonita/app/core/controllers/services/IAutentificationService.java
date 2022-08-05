package com.cryptonita.app.core.controllers.services;

import com.cryptonita.app.dto.data.request.UserRegisterDTO;

public interface IAutentificationService {

    public UserRegisterDTO register(UserRegisterDTO userRegisterDTO);

}
