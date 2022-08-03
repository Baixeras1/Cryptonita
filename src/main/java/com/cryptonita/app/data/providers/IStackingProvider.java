package com.cryptonita.app.data.providers;

import com.cryptonita.app.dto.response.StackingDTO;

import java.util.List;

public interface IStackingProvider {

    StackingDTO stake(String userName,String coinName,double quantity,int daysToExpire);
    StackingDTO unStake(String userName,long id);
    List<StackingDTO> getAllUserStakes(String username);
    StackingDTO getUserStake(long id,String username);

}
