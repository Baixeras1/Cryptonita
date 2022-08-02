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
    public int rank;
}
