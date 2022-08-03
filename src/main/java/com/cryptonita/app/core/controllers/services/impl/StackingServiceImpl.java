package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IStackingService;
import com.cryptonita.app.data.daos.IStackingDAO;
import com.cryptonita.app.data.daos.IUserDao;
import com.cryptonita.app.data.entities.StackingModel;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.data.providers.mappers.impl.StackingResponseMapper;
import com.cryptonita.app.dto.response.StackingDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StackingServiceImpl implements IStackingService {

    private final IMapper<StackingModel,StackingDTO> stackingDTOIMapper;

    private final IUserDao userDao;

    private final IStackingDAO stackingDAO;


    @Override
    public List<StackingDTO> findAll() {
        return null;
    }

    @Override
    public List<StackingDTO> findAllByUser(String username) {
        return null;
    }

    @Override
    public StackingDTO stake(long id, String username, double quantity) {
        return null;
    }

    @Override
    public StackingDTO unStake(long id, String username) {
        return null;
    }

    @Override
    public StackingDTO find(long id, String username) {
        return null;
    }
}
