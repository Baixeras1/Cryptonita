package com.cryptonita.app.security;


import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class UserManagerServiceImpl implements AuthenticationProvider {

    private final IUserProvider userProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        //Call provider and perform authetificastion

        UserResponseDTO dto = userProvider.getByName(name);

        if (!userProvider.matchesPasswordByUsername(name, password))
            throw new BadCredentialsException("");

        return new UsernamePasswordAuthenticationToken(dto,null, Collections.singletonList(dto.role::name));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
