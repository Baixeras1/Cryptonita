package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IAssetsService;
import com.cryptonita.app.data.providers.ICoinProvider;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.controller.CoinDto;
import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import com.cryptonita.app.dto.integration.CandleInfoDTO;
import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import com.cryptonita.app.integration.services.ICoinIntegrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AssetsServiceImpl implements IAssetsService {

    private final ICoinProvider coinProvider;

    private final ICoinIntegrationService coinService;

    private final IMapper<CoinResponseDTO, Mono<CoinDto>> coinDTOMapper;
    private final IMapper<List<CoinResponseDTO>, Flux<CoinDto>> coinDTOManyMapper;

    @Override
    public Flux<CoinDto> getAll() {
        return coinDTOManyMapper.mapToDto(coinProvider.getAllCoins());
    }

    @Override
    public Mono<CoinDto> getById(long id) {
        return coinDTOMapper.mapToDto(coinProvider.getCoinById(id));
    }

    @Override
    public Mono<CoinDto> getBySymbol(String symbol) {
        return coinDTOMapper.mapToDto(coinProvider.getBySymbol(symbol));
    }

    @Override
    public Mono<CoinDto> getByName(String name) {
        return coinDTOMapper.mapToDto(coinProvider.getCoinByName(name));
    }

    @Override
    public Flux<HistoryInfoDTO> getAllHistory(String id, String vs_currency, String days, Optional<String> interval) {
        if (!interval.isPresent())
            return coinService.getHistoryOfCoin(id, vs_currency,days);

        return coinService.getHistoryOfCoin(id, vs_currency, days, interval.get());
    }


    @Override
    public Flux<CandleInfoDTO> getAllCandles(String id, String vs_currency, String days) {
        return coinService.getCandleOfCoin(id, vs_currency, days);
    }


}
