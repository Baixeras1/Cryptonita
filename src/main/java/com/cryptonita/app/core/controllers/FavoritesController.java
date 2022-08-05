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

    @GetMapping("/byName/{name}")
    public RestResponse getByName(@PathVariable String name) {
        return RestResponse.encapsulate(favoritesService.getByName(name));
    }

    @GetMapping("/byMail/{mail}")
    public RestResponse getByMail(@PathVariable String mail) {
        return RestResponse.encapsulate(favoritesService.getByMail(mail));
    }

    @GetMapping("/byId/{id}")
    public RestResponse getById(@PathVariable Long id) {
        return RestResponse.encapsulate(favoritesService.getById(id));
    }

    @PostMapping("/create")
    public RestResponse create(String name, String coin) {
        return RestResponse.encapsulate(favoritesService.create(name, coin));
    }

    @DeleteMapping("/delete")
    public RestResponse delete(String name, String coin) {
        return RestResponse.encapsulate(favoritesService.delete(name, coin));
    }

}
