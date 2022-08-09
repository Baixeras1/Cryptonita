package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.ISwapService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import com.cryptonita.app.dto.data.response.SwapResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/swap")
@AllArgsConstructor
public class SwapController {

    private final ISwapService swapService;

    @GetMapping("/change")
    public RestResponse changeCoins(String from, String to, Double amount) {
        return RestResponse.encapsulate(swapService.swap(from, to, amount));
    }

    @GetMapping("/tradeUsers")
    public RestResponse tradeUsers(String userTo,String from, String to, Double amount) {
        return RestResponse.encapsulate(swapService.swap(userTo,from,to,amount));
    }



}
