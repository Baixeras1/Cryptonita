package com.cryptonita.app.dto.data.response;

import com.cryptonita.app.dto.integration.CoinInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class CoinDetailsDTO {

    private String id;
    private String name;
    private double quantity;
    private CoinInfoDTO coinInfoDTO;

}
