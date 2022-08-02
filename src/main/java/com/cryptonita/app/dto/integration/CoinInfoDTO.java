package com.cryptonita.app.dto.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
public class CoinInfoDTO {

    public final String name;
    public final String symbol;
    public final int rank;

}
