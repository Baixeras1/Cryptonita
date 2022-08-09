package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IConvertorService;
import com.cryptonita.app.core.controllers.services.ISwapService;
import com.cryptonita.app.data.providers.IAccountProvider;
import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.dto.data.response.WalletResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SwapServiceImpl implements ISwapService {

    private final IUserProvider userProvider;
    private final IAccountProvider accountProvider;
    private final IConvertorService convertorService;

    @Override
    public void swap(String from, String to, double amount) {
        // TODO Get same user from security context
        swap(from, from, to, amount);  // Same target and source
    }

    @Override
    public void swap(String userTarget, String from, String to, double amount) {
        WalletResponseDto withdrawDto = accountProvider.withDraw(userTarget, from, amount);

        double amountToDeposit = amount * 0.985;
        WalletResponseDto depositDto = accountProvider.deposit("sergio.bernal", to, amountToDeposit);

        // TODO return something
    }

}
