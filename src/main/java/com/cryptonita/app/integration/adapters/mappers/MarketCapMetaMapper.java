package com.cryptonita.app.integration.adapters.mappers;

import com.cryptonita.app.dto.integration.CoinMetadataDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarketCapMetaMapper implements AdapterMapper<CoinMetadataDTO> {

    @Override
    public CoinMetadataDTO mapToDto(String s) {
        return null;
    }

    @Override
    public List<CoinMetadataDTO> mapManyToDto(String s) {
        return null;
    }
}
