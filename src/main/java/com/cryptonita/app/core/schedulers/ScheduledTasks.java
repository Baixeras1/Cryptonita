package com.cryptonita.app.core.schedulers;

import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class ScheduledTasks {

    private final IUserProvider userProvider;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 2628000000000L)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));

        List<UserResponseDTO> users = userProvider.getAll();
        for (UserResponseDTO user : users) {
            userProvider.restartUserNumRequest(user.getUsername());

        }


    }
}
