package com.cryptonita.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FavoritesResponseDto {

    private long id;
    private String userName;
    private String coinName;

}
