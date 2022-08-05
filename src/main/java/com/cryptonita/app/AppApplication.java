package com.cryptonita.app;

import com.cryptonita.app.core.loaders.CoinLoader;
import com.cryptonita.app.core.loaders.UsersLoader;
import com.cryptonita.app.data.providers.IAccountProvider;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import com.cryptonita.app.dto.integration.CoinInfoDTO;
import com.cryptonita.app.integration.websocket.CoinCapConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import reactor.core.publisher.Flux;

@Slf4j
@EnableAsync
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    CommandLineRunner init(
            CoinLoader coinLoader,
            UsersLoader usersLoader,
            IAccountProvider accountProvider,
            CoinCapConsumer coinCapConsumer
    ) {
        return (args) -> {
            Flux<CoinInfoDTO> coinFlux = coinLoader.load();
            Flux<UserResponseDTO> usersFlux = usersLoader.load();

            Flux.concat(coinFlux, usersFlux)
                    .doOnComplete(() -> {
                        accountProvider.create("sergio.bernal","Bitcoin");
                        accountProvider.deposit("sergio.bernal","Bitcoin",12);
                        coinCapConsumer.start();
                    })
                    .subscribe();
        };
    }

    private void callBack() {
    }

}
