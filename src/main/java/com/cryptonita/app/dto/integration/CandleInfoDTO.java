package com.cryptonita.app.dto.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
public class CandleInfoDTO {

    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;
    private double period;

}
