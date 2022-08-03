package com.cryptonita.app.core.controllers.services;

import com.cryptonita.app.dto.response.FavoritesResponseDto;

import java.util.List;

public interface IFavoritesService {


    List<FavoritesResponseDto> getById(long id);

    List<FavoritesResponseDto> getByMail(String mail);

    List<FavoritesResponseDto> getByName(String name);

    FavoritesResponseDto delete(String name,String coin);

    FavoritesResponseDto create(String name, String coin);

}
