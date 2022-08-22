package com.cryptonita.app.core.schedulers;

import com.cryptonita.app.data.providers.IRestartProvider;
import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class ScheduledTasks {

    private final IUserProvider userProvider;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final IRestartProvider restartProvider;

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));

        LocalDateTime localDateTime = LocalDateTime.now();
        //String mes = System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getYear());





        restartRequest(userProvider.getAll());


    }

    private void restartRequest(List<UserResponseDTO> users) {
        for (UserResponseDTO user : users) {
            userProvider.restartUserNumRequest(user.getUsername());
        }
    }
}
