package com.cryptonita.app.data.providers.impl;

import com.cryptonita.app.data.daos.IWalletDao;
import com.cryptonita.app.data.entities.WalletModel;
import com.cryptonita.app.data.providers.IAcountProvider;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.response.WallerResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AcountProviderImpl implements IAcountProvider {

    private IWalletDao walletDao;

    private IMapper<WalletModel,WallerResponseDto> walletMapper;

    @Override
    public WallerResponseDto get(String user, String coin) {
        return walletDao.findByCoin_NameAndAccount_User_Username(user,coin)
                .map(walletMapper::mapToDto)
                .orElse(null);
    }

    @Override
    public WallerResponseDto create(String user, String coin) {
        WalletModel walletModel = walletDao.findByCoin_NameAndAccount_User_Username(user,coin).orElse(null);

        if(walletModel == null)
            throw new RuntimeException();

        return walletMapper.mapToDto(walletDao.save(walletModel));
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
