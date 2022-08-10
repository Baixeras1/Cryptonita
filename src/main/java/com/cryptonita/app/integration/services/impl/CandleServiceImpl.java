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
    public Flux<CandleInfoDTO> getAll(String id, String vs_currency, String days) {
        return candleCapMetaAdapter.getCandleOfCoin(id, vs_currency, days);
    }

}
