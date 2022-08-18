package com.cryptonita.app.core.controllers.services;

import com.cryptonita.app.dto.controller.CoinDto;
import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import com.cryptonita.app.dto.integration.CandleInfoDTO;
import com.cryptonita.app.dto.integration.CoinInfoDTO;
import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * This class represents all the relevant methods to get information about User and Account from the database.
 */

public interface IAssetsService {

     List<CoinResponseDTO> list();

     Flux<CoinDto> getAll(Optional<String> ids);

     Mono<CoinDto> getById(String coinID);

     Mono<CoinDto> getByRank(int rank);

     Mono<CoinDto> getBySymbol(String symbol);

     Mono<CoinDto> getByName(String name);

     Flux<HistoryInfoDTO> getAllHistory(String id, String vs_currency, String days, Optional<String> interval);

     Flux<CandleInfoDTO> getAllCandles(String id, String vs_currency, String days);

}
