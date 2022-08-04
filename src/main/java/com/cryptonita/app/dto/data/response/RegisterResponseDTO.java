package com.cryptonita.app.dto.data.response;


import com.cryptonita.app.data.entities.UserModel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class RegisterResponseDTO {

    public final String user;
    public final LocalDateTime date;
    public final String origin;
    public final String destiny;
    public final double quantity;

}
