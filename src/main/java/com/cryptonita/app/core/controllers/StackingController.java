package com.cryptonita.app.core.controllers;


import com.cryptonita.app.core.controllers.services.IStackingService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import com.cryptonita.app.dto.data.response.StackingDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/stacking")
public class StackingController {

    private final IStackingService stackingService;

    @GetMapping("/all")
    public RestResponse getAll() {
        return RestResponse.encapsulate(stackingService.findAll());
    }

    @GetMapping("/allUser")
    public RestResponse getAllById(String username) {
        return RestResponse.encapsulate(stackingService.findAllByUser(username));
    }

    @PostMapping("/stake")
    public RestResponse stake(String username, String coinName, double quantity, int daysToExpire) {
        return RestResponse.encapsulate(stackingService.stake(username, coinName, quantity, daysToExpire));
    }

    @DeleteMapping("/unStake")
    public RestResponse unStake(long id, String username) {
        return RestResponse.encapsulate(stackingService.unStake(id, username));
    }

}
