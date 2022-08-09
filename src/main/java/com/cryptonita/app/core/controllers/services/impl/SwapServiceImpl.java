package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IConvertorService;
import com.cryptonita.app.core.controllers.services.ISwapService;
import com.cryptonita.app.data.entities.enums.UserType;
import com.cryptonita.app.data.providers.IAccountProvider;
import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import com.cryptonita.app.dto.data.response.WalletResponseDto;
import com.cryptonita.app.dto.integration.ConversorDTO;
import com.cryptonita.app.security.SecurityContextHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SwapServiceImpl implements ISwapService {

    private final IUserProvider userProvider;
    private final IAccountProvider accountProvider;
    private final IConvertorService convertorService;
    private final SecurityContextHelper securityContextHelper;

    @Override
    public void swap(String from, String to, double amount) {
        UserResponseDTO user = securityContextHelper.getUser();
        ConversorDTO conversorDTO = convertorService.convert(from,to,amount).block();


        accountProvider.deposit(user.username,to,conversorDTO.price);
        accountProvider.withDraw(user.username,from,amount);
    }

    @Override
    public void swap(String userTarget, String from, String to, double amount) {
        WalletResponseDto withdrawDto = accountProvider.withDraw(userTarget, from, amount);

        double amountToDeposit = amount * 0.985;
        WalletResponseDto depositDto = accountProvider.deposit("sergio.bernal", to, amountToDeposit);

        // TODO return something
    }

}
