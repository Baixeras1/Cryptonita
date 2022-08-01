package com.cryptonita.app.data.providers;

import com.cryptonita.app.dto.response.WallerResponseDto;

import java.util.List;


public interface IAcountProvider {

    WallerResponseDto get(String user,String coin);

    WallerResponseDto create(String user,String coin);

    WallerResponseDto deposit(String user, String coin, double ammount);

    WallerResponseDto withDraw(String user, String coin, double ammount);

    WallerResponseDto clear(String user, String coin);

    List<WallerResponseDto> getAllFromUser(String user);

}
