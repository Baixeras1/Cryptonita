package com.cryptonita.app.dto.response;


import com.cryptonita.app.data.entities.UserModel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class RegisterResponseDTO {

    public final String user;
    public final LocalDate date;
    public final String origin;
    public final String destiny;
    public final int quantity;

}
