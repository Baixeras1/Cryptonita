package com.cryptonita.app.security.utils;

import com.cryptonita.app.data.entities.enums.UserRole;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RatePerMonthMapService {

    private final LoadingCache<String, Integer> map = Caffeine.newBuilder()
            .expireAfterWrite(30, TimeUnit.DAYS)
            .refreshAfterWrite(30, TimeUnit.DAYS)
            .build(s -> 0);

    public void consume(UserResponseDTO userDTO, int tokens) {
        map.asMap().merge(userDTO.mail, tokens, Integer::sum);
    }

    public boolean isBlocked(UserResponseDTO userDTO) {
        if (userDTO.role == UserRole.ADMIN) return false;

        Integer value = map.get(userDTO.mail);
        return value != null && value >= userDTO.type.getRateLimitPerMonth();
    }

    public Integer getTokenConsumed(UserResponseDTO userDTO) {
        return map.get(userDTO.mail);
    }


}
