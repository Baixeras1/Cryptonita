package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IPorfolioService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import com.cryptonita.app.dto.data.response.PorfolioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/portfolio")
@CrossOrigin("*")
@Tag(name = "Portfolio")
public class PortfolioController {

    private final IPorfolioService porfolioService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieves information of a wallet identified with a coin name from the current user")
    public RestResponse get(String coin) {
        return RestResponse.encapsulate(porfolioService.get(coin));
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Gets the portfolio (totalBalance, coins, coin market data and allocation) for the current user ")
    public RestResponse getAll() {
        return RestResponse.encapsulate(porfolioService.getAll());
    }

    @GetMapping("/getPortfolio")
    public PorfolioResponseDTO getPortfolio() {
        return porfolioService.getPorfolio();
    }
}
