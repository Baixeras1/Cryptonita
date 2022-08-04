package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IFavoritesService;
import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.dto.data.response.FavoritesResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FavoritesServiceImpl implements IFavoritesService {

    private final IUserProvider userProvider;

    @Override
    public List<FavoritesResponseDto> getById(long id) {
        return userProvider.getById(id).getFavorites();
    }

    @Override
    public List<FavoritesResponseDto> getByMail(String mail) {
        return userProvider.getByEmail(mail).getFavorites();
    }

    @Override
    public List<FavoritesResponseDto> getByName(String name) {
        return userProvider.getByName(name).getFavorites();
    }

    @Override
    public FavoritesResponseDto delete(String name, String coin) {
        return userProvider.removeFavorite(name,coin);
    }

    @Override
    public FavoritesResponseDto create(String name, String coin) {
        return userProvider.addFavourite(name, coin);
    }
}
