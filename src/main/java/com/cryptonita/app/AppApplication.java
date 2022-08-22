package com.cryptonita.app;

import com.cryptonita.app.core.controllers.services.IPortfolioService;
import com.cryptonita.app.core.loaders.CoinLoader;
import com.cryptonita.app.core.loaders.UsersLoader;
import com.cryptonita.app.core.services.IEmailService;
import com.cryptonita.app.core.services.impl.EmailService;
import com.cryptonita.app.data.providers.IAccountProvider;
import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.dto.data.request.RegisterRequestDTO;
import com.cryptonita.app.integration.websocket.CoinCapConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.time.LocalDate;

@Slf4j
@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    CommandLineRunner init(
            CoinLoader coinLoader,
            UsersLoader usersLoader,
            IAccountProvider accountProvider,
            IPortfolioService portfolioService,
            IUserProvider userProvider,
            CoinCapConsumer coinCapConsumer

    ) {
        return (args) -> {
            coinCapConsumer.start(); // Starts websocket

            coinLoader.load().blockLast();
            usersLoader.load().blockLast();

            accountProvider.deposit("sergio.bernal", "Bitcoin", 12);

            accountProvider.deposit("sergio.bernal", "ethereum", 120);



        };
    }

    private void callBack() {
    }

}
