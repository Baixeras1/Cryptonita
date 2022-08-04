package com.cryptonita.app.core.services;

import com.cryptonita.app.integration.websocket.CoinCapPriceUpdateEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PriceSocket {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    public void listener(CoinCapPriceUpdateEvent e) {
        log.info("Received event for coin " + e.getCoin());

        String topic = String.format("/crypto/%s", e.getCoin());
        simpMessagingTemplate.convertAndSend(topic, e.getPrice());
    }

}