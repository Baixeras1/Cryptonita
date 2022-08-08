package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IPorfolioService;
import com.cryptonita.app.data.providers.IAccountProvider;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import com.cryptonita.app.dto.data.response.WallerResponseDto;
import com.cryptonita.app.security.SecurityContextHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PorfolioServiceImpl implements IPorfolioService {

    private final IAccountProvider acountProvider;
    private final SecurityContextHelper securityContextHelper;

    @Override
    public WallerResponseDto get(String coin) {
        return acountProvider.get(securityContextHelper.getUser().getUsername(),coin);
    }

    @Override
    public List<WallerResponseDto> getAll() {
        return acountProvider.getAllFromUser(securityContextHelper.getUser().getUsername());
    }
}
