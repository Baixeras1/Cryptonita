package com.cryptonita.app.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coin implements Serializable {

   private CoinData coin;

    public Coin() {

    }

    public CoinData getCoin() {
        return coin;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "coin=" + coin +
                '}';
    }
}
