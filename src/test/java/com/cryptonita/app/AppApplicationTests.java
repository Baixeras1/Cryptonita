package com.cryptonita.app;

import com.cryptonita.app.bean.Coin;
import com.cryptonita.app.controller.CoinController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppApplicationTests {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CoinController coinController;


    @Test
    public void getCoinsIsNotNull() throws Exception {
        assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/coins/all", Coin.class)).isNull();
    }

    @Test
    public void getCoinsIsNotEmpty() throws Exception {
        assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/coins/all", Coin.class)).isNotNull();
    }

    @Test
    public void getCoinsSizeIsEqualTo100() throws Exception {
        assertEquals("El size es 100", coinController.getCoins(restTemplate).size(), 100);
    }


}
