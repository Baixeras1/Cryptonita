package com.cryptonita.app.core.controllers.services.impl;


import com.cryptonita.app.core.controllers.services.IAdminService;
import com.cryptonita.app.data.providers.ICoinProvider;
import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.dto.data.response.BannedUserResponseDTO;
import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import com.cryptonita.app.exceptions.data.CoinNotFoundException;
import com.cryptonita.app.security.SecurityContextHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AdminServiceImpl implements IAdminService {

    private final ICoinProvider coinProvider;
    private final IUserProvider userProvider;
    private final SecurityContextHelper securityContextHelper;


    @Override
    public CoinResponseDTO createCoin(String name,String symbol,int rank) {
        //log.info(); //TODO
        return coinProvider.createCoin(name, symbol,rank);
    }

    @Override
    public CoinResponseDTO deleteCoin(String name) {
        return coinProvider.deleteByName(name);
    }

    @Override
    public BannedUserResponseDTO banUser(String mail) {
        return userProvider.banUser(mail);
    }

    @Override
    public BannedUserResponseDTO unBanUser(String mail) {
        return userProvider.unBanUser(mail);
    }

    @Override
    public UserResponseDTO getUserById(long id) {
        return userProvider.getById(id);
    }
}
