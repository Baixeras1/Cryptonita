package com.cryptonita.app.service;

import com.cryptonita.app.bean.Coin;
import com.cryptonita.app.bean.Coins;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoinService {

    public Coin getCoinById(RestTemplate restTemplate, String id) {

        String url = "https://api.coincap.io/v2/assets/";

        ResponseEntity<Coin> response
                = restTemplate.getForEntity(url + id, Coin.class);

        return response.getBody();
    }

    public Coins getCoins(RestTemplate restTemplate) {

        ResponseEntity<Coins> response = restTemplate.getForEntity("https://api.coincap.io/v2/assets", Coins.class);


        Coins coins = response.getBody();

        return coins;
    }

}


