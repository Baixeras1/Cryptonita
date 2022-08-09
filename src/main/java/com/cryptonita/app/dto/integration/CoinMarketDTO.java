package com.cryptonita.app.dto.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
public class CoinMarketDTO {

    public String name;
    public String symbol;
    public String logo;
    public double supply;
    public double maxSupply;
    public double marketCapUsd;
    public double totalVolume;
    public double priceUsd;
    public double changePercent24Hr;

}
