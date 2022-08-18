package com.cryptonita.app.security.filters;

import com.cryptonita.app.core.services.IEmailService;
import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import com.cryptonita.app.security.SecurityContextHelper;
import com.cryptonita.app.security.utils.LoginAttemptsService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class BannedIPFilter extends OncePerRequestFilter {

    private LoginAttemptsService loginAttemptsService;


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (loginAttemptsService.isBlocked(getClientIP(request))) {
            System.out.println("Too many request!!!");
            return;
        }

        filterChain.doFilter(request, response);

    }

        private String getClientIP(HttpServletRequest request) {
            String xfHeader = request.getHeader("X-Forwarded-For");
            if (xfHeader == null){
                return request.getRemoteAddr();
            }
            return xfHeader.split(",")[0];
        }

}
