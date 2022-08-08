package com.cryptonita.app.core.controllers.services;

import com.cryptonita.app.dto.data.response.BannedUserResponseDTO;
import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import org.h2.engine.User;

public interface IAdminService {

    CoinResponseDTO createCoin(String name,String symbol,int rank);

    CoinResponseDTO deleteCoin(String name);

    BannedUserResponseDTO banUser(String mail);

    BannedUserResponseDTO unBanUser(String mail);

    UserResponseDTO getUserById(long id);

}
