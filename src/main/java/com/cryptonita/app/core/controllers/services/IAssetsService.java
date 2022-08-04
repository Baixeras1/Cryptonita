package com.cryptonita.app.core.controllers.services;

import com.cryptonita.app.dto.integration.CandleInfoDTO;
import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import com.cryptonita.app.dto.response.CoinResponseDTO;

import java.util.List;

/**
 * This class represents all the relevant methods to get information about User and Account from the database.
 */

public interface IAssetsService {

     List<CoinResponseDTO> getAll();

     CoinResponseDTO getById(long id);

     CoinResponseDTO getBySymbol(String symbol);

     CoinResponseDTO getByName(String name);

     List<HistoryInfoDTO> getAllHistorys(String symbol, String interval, Long start, Long end);

     List<HistoryInfoDTO> getAllHistorys(String symbol, String interval);

     List<CandleInfoDTO> getAllCandles(String exchange, String interval, String baseId, String quoteId, Long start, Long end);

     List<CandleInfoDTO> getAllCandles(String exchange, String interval, String baseId, String quoteId);

}
