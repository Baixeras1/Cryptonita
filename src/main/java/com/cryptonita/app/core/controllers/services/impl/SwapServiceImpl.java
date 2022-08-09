package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IConvertorService;
import com.cryptonita.app.core.controllers.services.ISwapService;
import com.cryptonita.app.data.entities.enums.UserType;
import com.cryptonita.app.data.providers.IAccountProvider;
import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.dto.data.response.SwapResponseDto;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import com.cryptonita.app.dto.data.response.WalletResponseDto;
import com.cryptonita.app.dto.integration.ConversorDTO;
import com.cryptonita.app.security.SecurityContextHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SwapServiceImpl implements ISwapService {

    private final IUserProvider userProvider;
    private final IAccountProvider accountProvider;
    private final IConvertorService convertorService;
    private final SecurityContextHelper securityContextHelper;


    @Transactional
    @Override
    public SwapResponseDto swap(String from, String to, double amount) {
        UserResponseDTO user = securityContextHelper.getUser();
        ConversorDTO conversorDTO = convertorService.convert(from, to, amount).block();

        double toDeposit = conversorDTO.price * user.getType().getComission();

        accountProvider.withDraw(user.username, from, amount);
        accountProvider.deposit(user.username, to, toDeposit);

        return SwapResponseDto.builder()
                .userName(user.username)
                .walletFrom(from)
                .walletTo(to)
                .amuountFrom(amount)
                .amountTo(toDeposit)
                .build();
    }

    @Override
    public SwapResponseDto swap(String userTarget, String from, String to, double amount) {
        WalletResponseDto withdrawDto = accountProvider.withDraw(userTarget, from, amount);

        double amountToDeposit = amount * 0.985;
        WalletResponseDto depositDto = accountProvider.deposit("sergio.bernal", to, amountToDeposit);

        return null;
        // TODO return something
    }

}
