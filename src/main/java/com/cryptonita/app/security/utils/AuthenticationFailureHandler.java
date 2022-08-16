package com.cryptonita.app.security.utils;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthenticationFailureHandler implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptsService loginAttemptsService;


    @SneakyThrows
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        System.out.println("woww");
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            loginAttemptsService.loginFailed(request.getRemoteAddr());
        } else {
            loginAttemptsService.loginFailed(xfHeader.split(",")[0]);
        }
    }
}
