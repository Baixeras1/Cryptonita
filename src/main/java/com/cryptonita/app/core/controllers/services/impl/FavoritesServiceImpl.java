package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IFavoritesService;
import com.cryptonita.app.data.providers.ICoinProvider;
import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.dto.response.FavoritesResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoritesServiceImpl implements IFavoritesService {

    private final IUserProvider userProvider;


    @Override
    public FavoritesResponseDto getByName(String name) {
        return null;
    }

    @Override
    public FavoritesResponseDto delete(String name, String coin) {
        return null;
    }

    @Override
    public FavoritesResponseDto create(FavoritesResponseDto favoritesResponseDto) {
        return null;
    }
}
