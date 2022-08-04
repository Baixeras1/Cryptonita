package com.cryptonita.app.dto.data.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WallerResponseDto {

    private long id;
    private String coinName;
    private float quality;

}
