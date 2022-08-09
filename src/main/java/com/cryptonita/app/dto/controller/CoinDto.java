package com.cryptonita.app.dto.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public final class CoinDto {

    public final long id;
    public final String name;
    public final String symbol;
    public final int rank;
    public final String logo;
    public final double supply;
    public final double maxSupply;
    public final double marketCapUsd;
    public final double totalVolumen;
    public final double priceUsd;
    public final double changePercent24Hr;

}
