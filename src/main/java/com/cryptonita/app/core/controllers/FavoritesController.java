package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IFavoritesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class FavoritesController {
    
    private final IFavoritesService favoritesService;
    
    
}
