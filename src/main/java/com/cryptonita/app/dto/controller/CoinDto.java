package com.cryptonita.app.dto.controller;

import com.cryptonita.app.dto.integration.CoinMarketIntegrationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public final class CoinDto {

    public final long rank;
    private final String id;
    public final String name;
    public final String symbol;
    public final CoinMarketIntegrationDTO marketData;

}
