package com.cryptonita.app.integration.services.impl;

import com.cryptonita.app.dto.integration.CandleInfoDTO;
import com.cryptonita.app.integration.adapters.ICandleCapMetaAdapter;
import com.cryptonita.app.integration.services.ICandleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class CandleServiceImpl implements ICandleService {

    private final ICandleCapMetaAdapter candleCapMetaAdapter;

    @Override
    public Flux<CandleInfoDTO> getAll(String exchange, String interval, String baseId, String quoteId, Long start, Long end) {
        return candleCapMetaAdapter.getCandleOfCoin(exchange, interval, baseId, quoteId, start, end);
    }

    @Override
    public Flux<CandleInfoDTO> getAll(String exchange, String interval, String baseId, String quoteId) {
        return candleCapMetaAdapter.getCandleOfCoin(exchange, interval, baseId, quoteId);
    }
}
