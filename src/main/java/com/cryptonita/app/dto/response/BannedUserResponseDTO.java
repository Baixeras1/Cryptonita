package com.cryptonita.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
public class BannedUserResponseDTO {

    public final long userID;
    public final String username;
    public final LocalDate bannedAt;
    public final LocalDate expiresAt;

}
