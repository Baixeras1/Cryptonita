package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IHistoryService;
import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/history")
public class HistoryController {

    private final IHistoryService historyService;

    @GetMapping("/history/{id}")
    public List<HistoryInfoDTO> getHistoryByUserName(String username, LocalDate start, LocalDate end){return null;}

    @GetMapping("/dowload")
    public HistoryInfoDTO dowloadHistory(String username,LocalDate start,LocalDate end){return null;}
}
