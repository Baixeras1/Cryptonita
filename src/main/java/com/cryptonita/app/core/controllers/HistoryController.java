package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IHistoryService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {

    private final IHistoryService historyService;

    @GetMapping("/history")
    public RestResponse getHistoryByUserName(LocalDate start, LocalDate end){
        return RestResponse.encapsulate(historyService.getAllRegisterUser(start,end));
    }

    @GetMapping("/dowload")
    public RestResponse dowloadHistory(LocalDate start,LocalDate end){return null;}
}
