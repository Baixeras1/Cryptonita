package com.cryptonita.app.service;

import com.cryptonita.app.bean.Coin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoinService {

    public Coin getCoinById(RestTemplate restTemplate, String uuid) {

        Coin coin = restTemplate.getForObject("https://api.coinranking.com/v2/coin/Qwsogvtv82FCd?x-access-token=coinranking798c1b4ec9657b5092e1bd04c620f4171c7e3787b03cb469", Coin.class);
        return coin;
    }

    public Coin[] getCoins(RestTemplate restTemplate) {

        ResponseEntity<Coin[]> response = restTemplate.getForEntity("https://api.coinranking.com/v2/coins/?x-access-token=coinranking798c1b4ec9657b5092e1bd04c620f4171c7e3787b03cb469", Coin[].class);

        Coin[] coins = response.getBody();

        return coins;
    }







}
// URL API
// https://api.coinranking.com/v2/coin/Qwsogvtv82FCd?x-access-token=coinranking798c1b4ec9657b5092e1bd04c620f4171c7e3787b03cb469

