package com.cryptonita.app.integration.adapters;

import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IHistoryAdapter {

    /**
     *
     * Retrieves a history of the desired crypto
     *
     * @param symbol the symbol of the coin to search
     * @param interval point-in-time interval.
     * @param start UNIX startup time in milliseconds.
     * @param end UNIX timeout in milliseconds.
     *
     * @return a reactive flux with the dtos carrying the info
     */
    Flux<HistoryInfoDTO> getHistoryOfCoin(String symbol, String interval, Long start, Long end);

    /**
     *
     * Retrieves a history of the desired crypto
     *
     * @param symbol the symbol of the coin to search
     * @param interval point-in-time interval.
     *
     * @return a reactive flux with the dtos carrying the info
     */
    Flux<HistoryInfoDTO> getHistoryOfCoin(String symbol, String interval);



}
