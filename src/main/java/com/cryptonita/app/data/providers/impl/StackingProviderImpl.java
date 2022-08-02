package com.cryptonita.app.data.providers.impl;

import com.cryptonita.app.data.daos.ICoinDAO;
import com.cryptonita.app.data.daos.IStackingDAO;
import com.cryptonita.app.data.daos.IUserDao;
import com.cryptonita.app.data.entities.CoinModel;
import com.cryptonita.app.data.entities.StackingModel;
import com.cryptonita.app.data.entities.UserModel;
import com.cryptonita.app.data.providers.ICoinProvider;
import com.cryptonita.app.data.providers.IStackingProvider;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.data.providers.mappers.impl.StackingResponseMapper;
import com.cryptonita.app.dto.response.CoinResponseDTO;
import com.cryptonita.app.dto.response.StackingDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class StackingProviderImpl implements IStackingProvider {

    private final IMapper<StackingModel, StackingDTO> stackingDTOIMapper;
    IStackingDAO stackingDAO;
    IUserDao userDao;
    ICoinDAO coinDAO;
    /**
     * Este metodo crea un stake
     * @param userName nombre de usuario
     * @param coinName nombre de la moneda de la que se hará el stake
     * @param quantity cantidad de dicha moneda
     * @return el stake creado
     */
    @Override
    public StackingDTO stake(String userName, String coinName, double quantity,int daysToExpire) {
        UserModel userModel = userDao.findByUsername(userName).orElse(null);
        if(userModel == null)
            throw new RuntimeException("El usuario no existe");
        CoinModel coinModel = coinDAO.findByName(coinName).orElse(null);
        if (coinModel==null)
            throw new RuntimeException("La moneda no existe");

        StackingModel stackingModel = StackingModel.builder()
                .user(userModel)
                .coin(coinModel)
                .createdAt(LocalDateTime.now())
                .quantity(quantity)
                .daysToExpire(daysToExpire)
                .build();


        stackingDAO.save(stackingModel);

        return stackingDTOIMapper.mapToDto(stackingModel);
    }

    @Override
    public StackingDTO unStake(String userName, long id) {

        UserModel userModel = userDao.findByUsername(userName).orElse(null);
        if(userModel == null)
            throw new RuntimeException("El usuario no existe");
        StackingModel stackingModel = stackingDAO.findById(id).orElse(null);


        StackingModel stackingModel = stackingDAO.findByUserUsername(userName).orElse(null);
        if (stackingModel == null)
            throw new RuntimeException("Ese usuario no tiene un stake con esa moneda");

       stackingDAO.delete(stackingModel);

        return stackingDTOIMapper.mapToDto(stackingModel);
    }

    @Override
    public StackingDTO unStakeQuantity(String userName, String coinName, double quantity) {
        return null;
    }

    @Override
    public StackingDTO getAllUserStakes(String username) {
        return null;
    }

    @Override
    public StackingDTO getUserStake(String username, String coinName) {
        return null;
    }
}
