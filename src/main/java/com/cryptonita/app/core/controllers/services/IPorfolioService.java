package com.cryptonita.app.core.controllers.services;

import com.cryptonita.app.dto.data.response.WallerResponseDto;

import java.util.List;

/**
 * This class represents all the relevant methods to get information about User and Account from the database.
 */
public interface IPorfolioService {
    WallerResponseDto get(String coin);

    List<WallerResponseDto> getAll();

}
