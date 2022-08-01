package com.cryptonita.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WallerResponseDto {

    private long id;
    private String coinName;
    private float quality;

}
