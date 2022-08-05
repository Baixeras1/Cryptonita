package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IConvertorService;
import com.cryptonita.app.data.providers.ICoinProvider;
import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import com.cryptonita.app.dto.integration.ConversorDTO;
import com.cryptonita.app.integration.services.ICoinConversorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ConvertorServiceImpl implements IConvertorService {

    private final ICoinConversorService coinConversorService;
    private final ICoinProvider coinProvider;

    @Override
    public Mono<ConversorDTO> convert(String from, double amount) {
        CoinResponseDTO fromCoin = coinProvider.getCoinByName(from);

        return coinConversorService.convert(fromCoin.symbol, amount);
    }

    @Override
    public Mono<ConversorDTO> convert(String from, String to, double amount) {
        CoinResponseDTO fromCoin = coinProvider.getCoinByName(from);
        CoinResponseDTO toCoin = coinProvider.getCoinByName(to);

        return coinConversorService.convert(fromCoin.symbol, toCoin.symbol, amount);
    }

}
