package com.cryptonita.app.core.controllers.services;

import com.cryptonita.app.dto.response.StackingDTO;

import java.util.List;

public interface IStackingService {

    List<StackingDTO> findAll();

    List<StackingDTO> findAllByUser(String username);

    StackingDTO stake(long id,String username, double quantity);

    StackingDTO unStake(long id,String username);

    StackingDTO find(long id,String username);
}
