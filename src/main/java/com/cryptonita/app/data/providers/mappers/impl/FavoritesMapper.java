package com.cryptonita.app.data.providers.mappers.impl;

import com.cryptonita.app.data.entities.CoinModel;
import com.cryptonita.app.data.entities.FavouritesModel;
import com.cryptonita.app.data.entities.UserModel;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.response.FavoritesResponseDto;
import org.springframework.stereotype.Component;

@Component
public class FavoritesMapper implements IMapper<FavouritesModel, FavoritesResponseDto> {

    @Override
    public FavoritesResponseDto mapToDto(FavouritesModel favouritesModel) {
        return FavoritesResponseDto.builder()
                .id(favouritesModel.getId())
                .userName(favouritesModel.getUser().getUsername())
                .coinName(favouritesModel.getCoin().getName())
                .build();
    }

    @Override
    public FavouritesModel mapToEntity(FavoritesResponseDto favoritesResponseDto) {
        CoinModel coin = CoinModel.builder()
                .name(favoritesResponseDto.getCoinName())
                .build();

        UserModel user = UserModel.builder()
                .username(favoritesResponseDto.getUserName())
                .build();

        return FavouritesModel.builder()
                .coin(coin)
                .user(user)
                .build();
    }
}
