package com.cryptonita.app.data.providers.impl;

import com.cryptonita.app.data.daos.ICoinDAO;
import com.cryptonita.app.data.entities.CoinModel;
import com.cryptonita.app.data.providers.ICoinProvider;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CoinProviderImpl implements ICoinProvider {

    private final ICoinDAO coinDAO;
    private final IMapper<CoinModel,CoinResponseDTO> responseDTOIMapper;

    @Override
    public CoinResponseDTO createCoin(String name, String symbol, int rank) {
        if(coinDAO.findByName(name).isPresent())
            throw new RuntimeException("Esa moneda ya existe");

       CoinModel coin =  CoinModel.builder()
               .name(name)
               .symbol(symbol)
               .rank(rank)
               .build();

       coin = coinDAO.save(coin);

        return responseDTOIMapper.mapToDto(coin);
    }

    @Override
    public List<CoinResponseDTO> getAllCoins() {
        List<CoinModel> listaCoin = coinDAO.findAll();
        List<CoinResponseDTO> listaDTO = new ArrayList<>();

        for (CoinModel coinModel : listaCoin) {
            CoinResponseDTO dto = responseDTOIMapper.mapToDto(coinModel);
            listaDTO.add(dto);
        }
        return listaDTO;
    }

    @Override
    @Transactional
    public CoinResponseDTO deleteByName(String name) {
        CoinModel coin = coinDAO.findByName(name).orElse(null);
        if(coin == null)
            throw new RuntimeException("Esa moneda no existe");

        coinDAO.deleteByName(name);

        return responseDTOIMapper.mapToDto(coin);
    }

    @Override
    public CoinResponseDTO getCoinByName(String name) {
        CoinModel coin = coinDAO.findByName(name).orElse(null);
        if(coin == null)
            throw new RuntimeException("Esa moneda no existe");
        return responseDTOIMapper.mapToDto(coin);
    }

    @Override
    public CoinResponseDTO getCoinById(long id) {
        CoinModel coin = coinDAO.findById(id).orElse(null);
        if(coin == null)
            throw new RuntimeException("Esa moneda no existe");

        return responseDTOIMapper.mapToDto(coin);


    }

    @Override
    public CoinResponseDTO getByRank(int rank) {
        CoinModel coin = coinDAO.findByRank(rank).orElse(null);
        if (coin == null)
            throw  new RuntimeException("Esa moneda no tiene rango");
        return responseDTOIMapper.mapToDto(coin);
    }

    @Override
    public CoinResponseDTO getBySymbol(String symbol) {
        CoinModel coin = coinDAO.findBySymbol(symbol).orElse(null);
        if (coin == null)
            throw  new RuntimeException("Esa moneda no existe");
        return responseDTOIMapper.mapToDto(coin);
    }
}
