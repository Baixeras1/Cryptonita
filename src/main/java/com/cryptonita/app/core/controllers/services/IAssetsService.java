package com.cryptonita.app.core.controllers.services;

import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import com.cryptonita.app.dto.integration.CandleInfoDTO;
import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * This class represents all the relevant methods to get information about User and Account from the database.
 */

public interface IAssetsService {

     List<CoinResponseDTO> getAll();

     CoinResponseDTO getById(long id);

     CoinResponseDTO getBySymbol(String symbol);

     CoinResponseDTO getByName(String name);

     Flux<HistoryInfoDTO> getAllHistorys(String symbol, String interval, Long start, Long end);

     Flux<HistoryInfoDTO> getAllHistorys(String symbol, String interval);

     Flux<CandleInfoDTO> getAllCandles(String exchange, String interval, String baseId, String quoteId, Long start, Long end);

     Flux<CandleInfoDTO> getAllCandles(String exchange, String interval, String baseId, String quoteId);

}
