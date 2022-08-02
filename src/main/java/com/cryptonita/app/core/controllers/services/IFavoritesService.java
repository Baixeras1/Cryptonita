package com.cryptonita.app.core.controllers.services;

import com.cryptonita.app.dto.response.FavoritesResponseDto;

public interface IFavoritesService {

    FavoritesResponseDto getByName(String name);

    FavoritesResponseDto delete(String name,String coin);

    FavoritesResponseDto create(FavoritesResponseDto favoritesResponseDto);

}
