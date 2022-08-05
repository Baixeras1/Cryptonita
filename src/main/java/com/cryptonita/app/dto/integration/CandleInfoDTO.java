package com.cryptonita.app.dto.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
public class CandleInfoDTO {

    public final double open;
    public final double high;
    public final double low;
    public final double close;
    public final double volume;
    public final double period;

}
