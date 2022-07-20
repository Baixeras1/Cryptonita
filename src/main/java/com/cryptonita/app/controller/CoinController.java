package com.cryptonita.app.controller;

import com.cryptonita.app.bean.Coin;
import com.cryptonita.app.bean.CoinData;
import com.cryptonita.app.bean.Coins;
import com.cryptonita.app.service.CoinService;
import com.cryptonita.app.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/coins")
public class CoinController {

    @Autowired
    private CoinService coinService;

    private final StorageService storageService;

    @Autowired
    public CoinController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/{id}")
    public Coin getCoinById(@PathVariable String id, RestTemplate restTemplate) {
        return coinService.getCoinById(restTemplate, id);
    }

    @GetMapping("/all")
    public Coins getCoins(RestTemplate restTemplate) {

        return coinService.getCoins(restTemplate);
    }
}
