package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IPorfolioService;
import com.cryptonita.app.data.providers.IAccountProvider;
import com.cryptonita.app.dto.response.WallerResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PorfolioServiceImpl implements IPorfolioService {

    private final IAccountProvider acountProvider;

    @Override
    public WallerResponseDto get(String user, String coin) {
        return acountProvider.get(user,coin);
    }

    @Override
    public List<WallerResponseDto> getAll(String user) {
        return acountProvider.getAllFromUser(user);
    }
}
