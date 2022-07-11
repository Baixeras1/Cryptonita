package com.cryptonita.app.controller;

import com.cryptonita.app.bean.Crypto;
import com.cryptonita.app.service.InfoCryptoService;
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
public class InfoCryptoController {

    @Autowired
    private InfoCryptoService infoCryptoService;

    @GetMapping("/{id}")
    public Crypto getCoinInfoById(@PathVariable String id, RestTemplate restTemplate) {
        return infoCryptoService.getCoinById(restTemplate, id);
    }

    @GetMapping("/all")
    public List<Crypto> getCoins(RestTemplate restTemplate){
        return Arrays.asList(infoCryptoService.getCoins(restTemplate));
    }

}
