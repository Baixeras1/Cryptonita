package com.cryptonita.app.data.providers.impl;

import com.cryptonita.app.data.daos.ICoinDAO;
import com.cryptonita.app.data.daos.IUserDao;
import com.cryptonita.app.data.daos.IWalletDao;
import com.cryptonita.app.data.entities.CoinModel;
import com.cryptonita.app.data.entities.UserModel;
import com.cryptonita.app.data.entities.WalletModel;
import com.cryptonita.app.data.providers.IAccountProvider;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.data.response.WallerResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountProviderImpl implements IAccountProvider {

    private IWalletDao walletDao;
    private IUserDao userDao;

    private ICoinDAO coinDAO;

    private IMapper<WalletModel,WallerResponseDto> walletMapper;

    @Override
    public WallerResponseDto get(String user, String coin) {
        return walletDao.findByCoin_NameAndAccount_User_Username(user,coin)
                .map(walletMapper::mapToDto)
                .orElse(null);
    }

    @Override
    public WallerResponseDto create(String user, String coin) {
        UserModel userModel = userDao.findByUsername(user).orElse(null);
        if(userModel == null)
            throw new RuntimeException("El usuario no existe");

        CoinModel coinModel = coinDAO.findByName(coin).orElse(null);
        if(coinModel == null)
            throw new RuntimeException("Esa moneda no existe");

        WalletModel walletModel = WalletModel.builder()
                .account(userModel.getAccount())
                .coin(coinModel)
                .build();

        if(walletModel == null)
            throw new RuntimeException("No tienes wallet");

        return walletMapper.mapToDto(walletDao.save(walletModel));
    }

    @Transactional
    @Override
    public WallerResponseDto deposit(String user, String coin, long ammount) {
        UserModel userModel = userDao.findByUsername(user).orElse(null);
        if(userModel == null)
            throw new RuntimeException("El usuario no existe");

        CoinModel coinModel = coinDAO.findByName(coin).orElse(null);
        if(coinModel == null)
            throw new RuntimeException("Esa moneda no existe");

        WalletModel wallet = userModel.getAccount().getWallets().get(coinModel);
        if(wallet == null)
            throw new RuntimeException();

        wallet.setQuantity(ammount);

        return walletMapper.mapToDto(walletDao.save(wallet));
    }

    @Override
    public WallerResponseDto withDraw(String user, String coin, long ammount) {
        UserModel userModel = userDao.findByUsername(user).orElse(null);
        if(userModel == null)
            throw new RuntimeException("El usuario no existe");

        CoinModel coinModel = coinDAO.findByName(coin).orElse(null);
        if(coinModel == null)
            throw new RuntimeException("Esa moneda no existe");

        WalletModel wallet = userModel.getAccount().getWallets().get(coinModel);
        if(wallet == null)
            throw new RuntimeException();

        wallet.setQuantity(wallet.getQuantity()-ammount);

        return walletMapper.mapToDto(walletDao.save(wallet));
    }


    @Override
    public WallerResponseDto clear(String user, String coin) {
        UserModel userModel = userDao.findByUsername(user).orElse(null);
        if(userModel == null)
            throw new RuntimeException("El usuario no existe");

        CoinModel coinModel = coinDAO.findByName(coin).orElse(null);
        if(coinModel == null)
            throw new RuntimeException("Esa moneda no existe");

        WalletModel wallet = userModel.getAccount().getWallets().get(coinModel);
        if(wallet == null)
            throw new RuntimeException();

        wallet.setQuantity(0);

        return walletMapper.mapToDto(walletDao.save(wallet));
    }

    @Override
    public List<WallerResponseDto> getAllFromUser(String user) {
        return walletDao.findAllByAccount_User_Username(user).stream()
                .map(walletMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
