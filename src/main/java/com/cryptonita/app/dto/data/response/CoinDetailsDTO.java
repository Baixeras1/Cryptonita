package com.cryptonita.app.dto.data.response;

import com.cryptonita.app.dto.integration.CoinInfoDTO;
import com.cryptonita.app.dto.integration.CoinMarketDTO;
import com.cryptonita.app.dto.integration.CoinMarketIntegrationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CoinDetailsDTO {

    private long id;
    private String name;
    private double quantity;
    private double allocation;
    private CoinMarketIntegrationDTO coinMarketIntegrationDTO;

}
