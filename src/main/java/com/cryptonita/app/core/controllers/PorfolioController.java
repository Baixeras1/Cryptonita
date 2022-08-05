package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IPorfolioService;
import com.cryptonita.app.dto.data.response.WallerResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/portfolio")
public class PorfolioController {

    private final IPorfolioService porfolioService;

    @GetMapping("/get")
    public WallerResponseDto get(String user,String coin) {
        return porfolioService.get(user,coin);
    }

    @GetMapping("/getAll")
    public List<WallerResponseDto> getAll(String user) {
        return porfolioService.getAll(user);
    }


}
