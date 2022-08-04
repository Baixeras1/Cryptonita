package com.cryptonita.app.core.loaders;

import com.cryptonita.app.data.providers.ICoinProvider;
import com.cryptonita.app.dto.integration.CoinInfoDTO;
import com.cryptonita.app.integration.services.ICoinInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class CoinLoader {

    private final ICoinProvider coinProvider;
    private final ICoinInfoService coinServiceInfo;

    /**
     * Retrieves all the coins info from an external api and saves
     * them on the database
     *
     * @return the flux that saves the coins
     */
    public Flux<CoinInfoDTO> load() {
        return coinServiceInfo.getAll()
                .doOnNext(coinInfoDTO -> coinProvider.createCoin(
                        coinInfoDTO.name,
                        coinInfoDTO.symbol,
                        coinInfoDTO.rank)
                );

    }

}
