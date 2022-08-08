package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IFavoritesService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import com.cryptonita.app.dto.data.response.FavoritesResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/favorites")
public class FavoritesController {
    
    private final IFavoritesService favoritesService;

    @GetMapping("/byName")
    public RestResponse getByName() {
        return RestResponse.encapsulate(favoritesService.getByName());
    }

    @PostMapping("/create")
    public RestResponse create(String coin) {
        return RestResponse.encapsulate(favoritesService.create(coin));
    }

    @DeleteMapping("/delete")
    public RestResponse delete(String coin) {
        return RestResponse.encapsulate(favoritesService.delete(coin));
    }

}
