package com.cryptonita.app.core.controllers.services;

import com.cryptonita.app.dto.response.StackingDTO;

import java.util.List;

public interface IStackingService {

    List<StackingDTO> findAll();

    List<StackingDTO> findAllByUser(String username);

    StackingDTO stake(String username,String coiname, double quantity,int daysToExpire);

    StackingDTO unStake(long id,String username);

    StackingDTO findUserStakeById(long id,String username);
}
