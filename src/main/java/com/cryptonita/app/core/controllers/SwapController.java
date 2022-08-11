package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.ISwapService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/swap")
@AllArgsConstructor
@Tag(name = "Swap")
public class SwapController {

    private final ISwapService swapService;

    @GetMapping("/trade")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Trades a certain amount of a coin to another in the portfolio of the current user")
    public RestResponse trade(String from, String to, Double amount) {
        return RestResponse.encapsulate(swapService.swap(from, to, amount));
    }

    @GetMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Send a certain amount of a coin from the current user to the given user")
    public RestResponse send(String userTo, String from, String to, Double amount) {
        return RestResponse.encapsulate(swapService.swap(userTo,from,to,amount));
    }



}
