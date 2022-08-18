package com.cryptonita.app.security.filters;

import com.cryptonita.app.data.providers.IUserProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class BannerUserFilter extends OncePerRequestFilter {

    private IUserProvider userProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username = request.getHeader("username");

        System.out.println("Hola?");

        if (!userProvider.isBannedByUsername(username)) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("El usuario " + username + " esta baneado");
    }

}
