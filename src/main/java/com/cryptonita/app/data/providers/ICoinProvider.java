package com.cryptonita.app.data.providers;

import com.cryptonita.app.dto.data.response.CoinResponseDTO;

import java.util.List;

public interface ICoinProvider {


    /**
     * This method creates a new coin in the database.
     * @param name name of the coin
     * @return  object created
     */
    CoinResponseDTO createCoin(String name, String symbol, int rank);

    /**
     * This method returns a list of all coins.
     * @return List of coins
     */
    List<CoinResponseDTO> getAllCoins();

    /**
     * This method deletes a coin by name
     * @param  name of coin
     * @return the deleted coin
     */
    CoinResponseDTO deleteByName(String name);

    /**
     * This method get a coin by name
     * @param name of coin
     * @return The called coin
     */
    CoinResponseDTO getCoinByName(String name);

    /**
     * This method get a coin by Id
     * @param id of coin
     * @return The called coin
     */
    CoinResponseDTO getCoinById(long id);

    CoinResponseDTO getByRank(int rank);

    CoinResponseDTO getBySymbol(String symbol);
}
