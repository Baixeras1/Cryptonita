package com.cryptonita.app.core.loaders;

import com.cryptonita.app.data.providers.ICoinProvider;
import com.cryptonita.app.dto.integration.CoinMarketIntegrationDTO;
import com.cryptonita.app.integrationv2.services.ICoinService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class CoinLoader {

    private final ICoinProvider coinProvider;
    private final ICoinService coinService;

    /**
     * Retrieves all the coins info from an external api and saves
     * them on the database
     *
     * @return the flux that saves the coins
     */
    public Flux<CoinMarketIntegrationDTO> load() {
        return coinService.getAllMarkets()
                .doOnNext(marketDTO -> {
                            coinProvider.createCoin(
                                    marketDTO.getId(),
                                    marketDTO.getName(),
                                    marketDTO.getSymbol());
                        }
                );

    }

}
