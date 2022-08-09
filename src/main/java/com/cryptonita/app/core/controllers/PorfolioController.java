package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IPorfolioService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/porfolio")
public class PorfolioController {

    private final IPorfolioService porfolioService;

    @GetMapping("/get")
    public RestResponse get(String coin) {
        return RestResponse.encapsulate(porfolioService.get(coin));
    }

    @GetMapping("/getAll")
    public RestResponse getAll() {
        return RestResponse.encapsulate(porfolioService.getAll());
    }


}
