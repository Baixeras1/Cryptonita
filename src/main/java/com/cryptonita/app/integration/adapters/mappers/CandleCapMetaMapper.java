package com.cryptonita.app.integration.adapters.mappers;

import com.cryptonita.app.dto.integration.CandleInfoDTO;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class CandleCapMetaMapper implements AdapterMapper<CandleInfoDTO>{

    private final JsonMapper jsonMapper;

    @Override
    public CandleInfoDTO mapToDto(String s) {
        return null;
    }

    @Override
    public List<CandleInfoDTO> mapManyToDto(String s) {
        return null;
    }
}
