package com.cryptonita.app.integration.adapters;

import com.cryptonita.app.dto.integration.CandleInfoDTO;
import reactor.core.publisher.Flux;

public interface ICandleCapMetaAdapter {

    /**
     *
     * Retrieves a candle of the desired crypto
     *
     * @param exchange 	exchange id
     * @param interval candle interval
     * @param baseId base id
     * @param quoteId quote id
     * @param start UNIX startup time in milliseconds.
     * @param end UNIX timeout in milliseconds.
     *
     * @return a reactive flux with the dtos carrying the info
     */

    Flux<CandleInfoDTO> getCandleOfCoin(String exchange, String interval, String baseId, String quoteId, Long start, Long end);

    /**
     *
     * Retrieves a candle of the desired crypto
     *
     * @param exchange 	exchange id
     * @param interval candle interval
     * @param baseId base id
     * @param quoteId quote id
     *
     * @return a reactive flux with the dtos carrying the info
     */

    Flux<CandleInfoDTO> getCandleOfCoin(String exchange, String interval, String baseId, String quoteId);
}
