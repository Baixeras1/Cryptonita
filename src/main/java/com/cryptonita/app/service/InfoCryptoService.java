package com.cryptonita.app.service;

import com.cryptonita.app.bean.Crypto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InfoCryptoService {

    public Crypto getCoinById(RestTemplate restTemplate, String uuid) {

        Crypto crypto = restTemplate.getForObject(
                "https://api.coinranking.com/v2/coin/Qwsogvtv82FCd?x-access-token=coinranking798c1b4ec9657b5092e1bd04c620f4171c7e3787b03cb469", Crypto.class);
        return crypto;
    }

    public Crypto[] getCoins(RestTemplate restTemplate) {

        ResponseEntity<Crypto[]> response = restTemplate.getForEntity(
                "https://api.coinranking.com/v2/coins/?x-access-token=coinranking798c1b4ec9657b5092e1bd04c620f4171c7e3787b03cb469", Crypto[].class);

        Crypto[] cryptos = response.getBody();

        return cryptos;
    }
}
// URL API
// https://api.coinranking.com/v2/coin/Qwsogvtv82FCd?x-access-token=coinranking798c1b4ec9657b5092e1bd04c620f4171c7e3787b03cb469