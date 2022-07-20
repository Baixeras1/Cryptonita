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

    private String explorer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSupply() {
        return supply;
    }

    public void setSupply(double supply) {
        this.supply = supply;
    }

    public double getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(double maxSupply) {
        this.maxSupply = maxSupply;
    }

    public double getMarket() {
        return market;
    }

    public void setMarket(double market) {
        this.market = market;
    }

    public double getVolumeCapUsd() {
        return volumeCapUsd;
    }

    public void setVolumeCapUsd(double volumeCapUsd) {
        this.volumeCapUsd = volumeCapUsd;
    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public double getChangePercent24Hr() {
        return changePercent24Hr;
    }

    public void setChangePercent24Hr(double changePercent24Hr) {
        this.changePercent24Hr = changePercent24Hr;
    }

    public double getVwap24Hr() {
        return vwap24Hr;
    }

    public void setVwap24Hr(double vwap24Hr) {
        this.vwap24Hr = vwap24Hr;
    }

    public String getExplorer() {
        return explorer;
    }

    public void setExplorer(String explorer) {
        this.explorer = explorer;
    }

    public CoinData() {
    }

    public CoinData(String id, int rank, String symbol, String name, double supply, double maxSupply, double market, double volumeCapUsd, double priceUsd, double changePercent24Hr, double vwap24Hr, String explorer) {
        this.id = id;
        this.rank = rank;
        this.symbol = symbol;
        this.name = name;
        this.supply = supply;
        this.maxSupply = maxSupply;
        this.market = market;
        this.volumeCapUsd = volumeCapUsd;
        this.priceUsd = priceUsd;
        this.changePercent24Hr = changePercent24Hr;
        this.vwap24Hr = vwap24Hr;
        this.explorer = explorer;
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
                ", explorer='" + explorer + '\'' +
                '}';
    }
}
