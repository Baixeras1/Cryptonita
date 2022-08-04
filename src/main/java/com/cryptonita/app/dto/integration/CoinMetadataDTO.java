package com.cryptonita.app.dto.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
public class CoinMetadataDTO {

    public String name;
    public String symbol;
    public String logo;
    public String description;
    public double supply;
    public double maxSupply;
    public double marketCapUsd;
    public double volumeUsd24Hr;
    public double priceUsd;
    public double changePercent24Hr;
    public double vwap24Hr;

}
