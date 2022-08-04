package com.cryptonita.app.integration.services.impl;

import com.cryptonita.app.dto.integration.ConversorDTO;
import com.cryptonita.app.integration.adapters.IConvertorAdapter;
import com.cryptonita.app.integration.services.ICoinConversorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CoinConversorServiceImpl implements ICoinConversorService {

    private final IConvertorAdapter convertorAdapter;

    @Override
    public Mono<ConversorDTO> convert(String from, double quantity) {
        return convertorAdapter.convert(from, quantity);
    }

    @Override
    public Mono<ConversorDTO> convert(String from, String to, double quantity) {
        return convertorAdapter.convert(from, to, quantity);
    }
}
