package com.cryptonita.app.data.providers.impl;

import com.cryptonita.app.data.providers.IAcountProvider;
import com.cryptonita.app.dto.response.WallerResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcountProviderImpl implements IAcountProvider {

    @Override
    public WallerResponseDto get(String user, String coin) {
        return null;
    }

    @Override
    public WallerResponseDto create(String user, String coin) {
        return null;
    }

    @Override
    public WallerResponseDto deposit(String user, String coin, double ammount) {
        return null;
    }

    @Override
    public WallerResponseDto withDraw(String user, String coin, double ammount) {
        return null;
    }

    @Override
    public WallerResponseDto clear(String user, String coin) {
        return null;
    }

    @Override
    public List<WallerResponseDto> getAllFromUser(String user) {
        return null;
    }
}
