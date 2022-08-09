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
import com.cryptonita.app.exceptions.data.CoinNotFoundException;
import com.cryptonita.app.exceptions.data.UserNotFoundException;
import com.cryptonita.app.exceptions.data.WalletNotFoundException;
import lombok.AllArgsConstructor;
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

    private IMapper<WalletModel,WallerResponseDto> walletMapper;

    @Override
    public WallerResponseDto get(String user, String coin) {
        return walletDao.findByAccount_User_UsernameAndCoin_Name(user,coin)
                .map(walletMapper::mapToDto)
                .orElseThrow(() -> new WalletNotFoundException("The Wallet dont exist"));
    }

    @Override
    public WallerResponseDto create(String user, String coin) {
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

    @Transactional
    @Override
    public WallerResponseDto deposit(String user, String coin, long ammount) {
        UserModel userModel = userDao.findByUsername(user)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_ALREADY_EXISTS,user)));

        CoinModel coinModel = coinDAO.findByName(coin)
                .orElseThrow(() -> new CoinNotFoundException(String.format(COIN_ALREADY_EXISTS,coin)));

        WalletModel wallet = userModel.getAccount().getWallets().get(coinModel);
        if(wallet == null)
            throw new WalletNotFoundException(WALLET_ALREADY_EXISTS);

        wallet.setQuantity(ammount);

        return walletMapper.mapToDto(walletDao.save(wallet));
    }

    @Override
    public WallerResponseDto withDraw(String user, String coin, long ammount) {
        UserModel userModel = userDao.findByUsername(user)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_ALREADY_EXISTS,user)));

        CoinModel coinModel = coinDAO.findByName(coin).
                orElseThrow(() -> new CoinNotFoundException(String.format(COIN_ALREADY_EXISTS,coin)));

        WalletModel wallet = userModel.getAccount().getWallets().get(coinModel);
        if(wallet == null)
           throw new WalletNotFoundException(WALLET_ALREADY_EXISTS);

        wallet.setQuantity(wallet.getQuantity()-ammount);

        return walletMapper.mapToDto(walletDao.save(wallet));
    }


    @Override
    public WallerResponseDto clear(String user, String coin) {
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
    public List<WallerResponseDto> getAllFromUser(String user) {
        return walletDao.findAllByAccount_User_Username(user).stream()
                .map(walletMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
