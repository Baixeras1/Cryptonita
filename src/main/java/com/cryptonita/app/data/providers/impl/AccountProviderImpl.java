package com.cryptonita.app.data.providers.impl;

import com.cryptonita.app.data.daos.ICoinDAO;
import com.cryptonita.app.data.daos.IUserDao;
import com.cryptonita.app.data.daos.IWalletDao;
import com.cryptonita.app.data.entities.CoinModel;
import com.cryptonita.app.data.entities.UserModel;
import com.cryptonita.app.data.entities.WalletModel;
import com.cryptonita.app.data.providers.IAccountProvider;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.data.response.WalletResponseDto;
import com.cryptonita.app.exceptions.data.CoinNotFoundException;
import com.cryptonita.app.exceptions.data.UserNotFoundException;
import com.cryptonita.app.exceptions.data.WalletNotFoundException;
import lombok.AllArgsConstructor;
import org.h2.engine.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountProviderImpl implements IAccountProvider {

    private static final String NO_COIN_FOUND = "The coin %s is not supported";
    private static final String COIN_ALREADY_EXISTS = "The coin %s already exists!";
    private static final String USER_ALREADY_EXISTS = "The user %s already exists!";
    private static final String WALLET_ALREADY_EXISTS = "The wallet already exists!";

    private IWalletDao walletDao;
    private IUserDao userDao;

    private ICoinDAO coinDAO;

    private IMapper<WalletModel, WalletResponseDto> walletMapper;

    @Override
    public WalletResponseDto get(String user, String coin) {
        return walletDao.findByAccount_User_UsernameAndCoin_Name(user,coin)
                .map(walletMapper::mapToDto)
                .orElseThrow(() -> new WalletNotFoundException("The Wallet dont exist"));
    }

    @Override
    public WalletResponseDto create(String user, String coin) {
        UserModel userModel = userDao.findByUsername(user)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_ALREADY_EXISTS,user)));

        CoinModel coinModel = coinDAO.findByName(coin).
                orElseThrow(() -> new CoinNotFoundException(String.format(COIN_ALREADY_EXISTS,coin)));

        WalletModel walletModel = WalletModel.builder()
                .account(userModel.getAccount())
                .coin(coinModel)
                .build();


        return walletMapper.mapToDto(walletDao.save(walletModel));
    }


    @Override
    public WalletResponseDto deposit(String user, String coin, double amount) {
        UserModel userModel = userDao.findByUsername(user)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_ALREADY_EXISTS,user)));

        CoinModel coinModel = coinDAO.findByName(coin)
                .orElseThrow(() -> new CoinNotFoundException(String.format(COIN_ALREADY_EXISTS,coin)));

        WalletModel walletModel = userModel.getAccount().getWallets().get(coinModel);

        if(walletModel == null) {
            walletModel = WalletModel.builder()
                    .account(userModel.getAccount())
                    .coin(coinModel)
                    .quantity(0)
                    .build();
        }

        walletModel.setQuantity(walletModel.getQuantity()+amount);

        return walletMapper.mapToDto(walletDao.save(walletModel));
    }

    @Override
    public WalletResponseDto withDraw(String user, String coin, double amount) {
        UserModel userModel = userDao.findByUsername(user)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_ALREADY_EXISTS,user)));

        CoinModel coinModel = coinDAO.findByName(coin).
                orElseThrow(() -> new CoinNotFoundException(String.format(COIN_ALREADY_EXISTS,coin)));

        WalletModel walletModel = userModel.getAccount().getWallets().get(coinModel);

        if(walletModel == null)
           throw new WalletNotFoundException("This Wallet doesnt exist");

        if(walletModel.getQuantity()<amount)
            throw new WalletNotFoundException("This wallet doesnt have sufficient funds ");

        walletModel.setQuantity(walletModel.getQuantity()-amount);

        return walletMapper.mapToDto(walletDao.save(walletModel));
    }


    @Override
    public WalletResponseDto clear(String user, String coin) {
        UserModel userModel = userDao.findByUsername(user)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_ALREADY_EXISTS,user)));

        CoinModel coinModel = coinDAO.findByName(coin)
                .orElseThrow(() -> new CoinNotFoundException(String.format(COIN_ALREADY_EXISTS,coin)));

        WalletModel wallet = userModel.getAccount().getWallets().get(coinModel);
        if(wallet == null)
            throw new WalletNotFoundException(WALLET_ALREADY_EXISTS);

        wallet.setQuantity(0);

        return walletMapper.mapToDto(walletDao.save(wallet));
    }

    @Override
    public List<WalletResponseDto> getAllFromUser(String user) {
        return walletDao.findAllByAccount_User_Username(user).stream()
                .map(walletMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
