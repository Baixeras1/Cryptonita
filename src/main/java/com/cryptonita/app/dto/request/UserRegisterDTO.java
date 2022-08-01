package com.cryptonita.app.dto.request;

import com.cryptonita.app.data.entities.enums.UserRole;
import com.cryptonita.app.data.entities.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class UserRegisterDTO {

    public final String mail;
    public final String username;
    public final String password;
    public final UserRole role;
    public final UserType type;

}
