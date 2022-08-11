package com.cryptonita.app.dto.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
public class PorfolioResponseDTO {

    private double balance = generarBalance();
    private List<CoinDetailsDTO> coinDetailsDTOList;

    public double generarBalance() {
        return 0;
    }


}
