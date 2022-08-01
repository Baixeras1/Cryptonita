package com.cryptonita.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoinResponseDTO {

    public long id;
    public String name;
    public String symbol;
    public String logo;
    public String description;
    public long supply;
    public long maxSupply;
    public long marketCapUsd;
    public long volumenUsd24Hr;
    public double price;
    public double changePercent24Hr;
    public double vwap24hr;
}
