package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IHistoryService;
import com.cryptonita.app.dto.data.response.RegisterResponseDTO;
import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import com.cryptonita.app.dto.request.RegisterRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {

    private final IHistoryService historyService;

    @GetMapping("/history/{id}")
    public List<RegisterResponseDTO> getHistoryByUserName(String username, LocalDate start, LocalDate end){
        return historyService.getAllRegisterUser(username,start,end);
    }

    @GetMapping("/dowload")
    public HistoryInfoDTO dowloadHistory(String username,LocalDate start,LocalDate end){return null;}
}
