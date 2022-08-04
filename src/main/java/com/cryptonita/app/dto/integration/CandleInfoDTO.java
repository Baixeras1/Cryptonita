package com.cryptonita.app.dto.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
public class CandleInfoDTO {

    private long open;
    private long high;
    private long low;
    private long close;
    private long volume;
    private long period;

}
