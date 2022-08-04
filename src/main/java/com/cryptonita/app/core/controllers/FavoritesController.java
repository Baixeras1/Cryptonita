package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IFavoritesService;
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
    public List<FavoritesResponseDto> getByName(@PathVariable String name) {
        return favoritesService.getByName(name);
    }

    @GetMapping("/byMail/{mail}")
    public List<FavoritesResponseDto> getByMail(@PathVariable String mail) {
        return favoritesService.getByMail(mail);
    }

    @GetMapping("/byId/{id}")
    public List<FavoritesResponseDto> getById(@PathVariable Long id) {
        return favoritesService.getById(id);
    }

    @PostMapping("/create")
    public FavoritesResponseDto create(String name, String coin) {
        return favoritesService.create(name, coin);
    }

    @DeleteMapping("/delete")
    public FavoritesResponseDto delete(String name, String coin) {
        return favoritesService.delete(name, coin);
    }



    
    
}
