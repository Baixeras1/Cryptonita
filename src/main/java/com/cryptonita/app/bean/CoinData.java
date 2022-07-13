package com.cryptonita.app.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinData implements Serializable {

    private String id;
    private int rank;
    private String symbol;
    private String name;
    private double supply;
    private double maxSupply;
    private double market;
    private double volumeCapUsd;
    private double priceUsd;
    private double changePercent24Hr;
    private double vwap24Hr;



    public String getId() {
        return id;
    }

    public int getRank() {
        return rank;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getSupply() {
        return supply;
    }

    public double getMaxSupply() {
        return maxSupply;
    }

    public double getMarket() {
        return market;
    }

    public double getVolumeCapUsd() {
        return volumeCapUsd;
    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public double getChangePercent24Hr() {
        return changePercent24Hr;
    }

    public double getVwap24Hr() {
        return vwap24Hr;
    }

    @Override
    public String toString() {
        return "CoinData{" +
                "id='" + id + '\'' +
                ", rank=" + rank +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", supply=" + supply +
                ", maxSupply=" + maxSupply +
                ", market=" + market +
                ", volumeCapUsd=" + volumeCapUsd +
                ", priceUsd=" + priceUsd +
                ", changePercent24Hr=" + changePercent24Hr +
                ", vwap24Hr=" + vwap24Hr +
                '}';
    }
}
