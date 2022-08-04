package com.cryptonita.app.integration.websocket;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Service
@AllArgsConstructor
public class CoinCapConsumer {

    private static final String URL = "wss://ws.coincap.io/prices?assets=ALL";

    private final CoinCapPriceHandler handler;

    public void start() {
        WebSocketConnectionManager connectionManager = new WebSocketConnectionManager(
                new StandardWebSocketClient(),
                handler,
                URL);

        connectionManager.start();
    }

}
