package com.cryptonita.app.data.providers;

import com.cryptonita.app.dto.response.StackingDTO;

public interface IStackingProvider {

    StackingDTO stake(String userName,String coinName,double quantity,int daysToExpire);
    StackingDTO unStake(String userName,long id);
    StackingDTO unStakeQuantity(String userName,String coinName,double quantity);
    StackingDTO getAllUserStakes(String username);
    StackingDTO getUserStake(String username,String coinName);

}
