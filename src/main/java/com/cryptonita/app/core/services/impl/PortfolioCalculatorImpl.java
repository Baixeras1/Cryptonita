package com.cryptonita.app.core.services.impl;

import com.cryptonita.app.core.services.IPortfolioCalculator;
import com.cryptonita.app.data.providers.IRegisterProvider;
import com.cryptonita.app.data.providers.mappers.IMapper;
import com.cryptonita.app.dto.data.response.HistoryResponseDTO;
import com.cryptonita.app.dto.data.response.PortfolioResponseDTO;
import com.cryptonita.app.dto.data.response.WalletResponseDto;
import com.cryptonita.app.dto.integration.HistoryInfoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@AllArgsConstructor
public class PortfolioCalculatorImpl implements IPortfolioCalculator {

    private static final long MILLIS_IN_A_DAY = 24 * 60 * 60 * 1000;
    private static final ZoneId gmt = ZoneId.of("GMT");

    private final IRegisterProvider registerProvider;
    private final IMapper<Map<String, WalletResponseDto>, PortfolioResponseDTO> mapper;

    @Override
    public List<HistoryInfoDTO> calculateAllTime(String username) {

        List<HistoryResponseDTO> histories = registerProvider.getAllLogsFromUser(username);
        if (histories.isEmpty()) return new ArrayList<>();

        LocalDate firstDate = histories.get(0).date;
        HistoryResponseDTO last = histories.get(histories.size() - 1);

        List<HistoryInfoDTO> chartList = getGranularityList(firstDate, LocalDate.now());

        int currentIndex = 0;
        HistoryResponseDTO current = histories.get(currentIndex);
        for (HistoryInfoDTO historyInfoDTO : chartList) {
            if ((currentIndex + 1) < histories.size()
                    && histories.get(currentIndex + 1).date.atStartOfDay(gmt).toEpochSecond() >= historyInfoDTO.time) {
                current = histories.get(++currentIndex);
            }


        }

        return chartList;
    }

    @Override
    public List<HistoryInfoDTO> calculateInterval(String username, LocalDate start, LocalDate end) {
        throw new UnsupportedOperationException(); //TODO
    }

    private List<HistoryInfoDTO> getGranularityList(LocalDate start, LocalDate end) {
        long epochStart = start.atStartOfDay(gmt).toEpochSecond();
        long epochEnd = end.atStartOfDay(gmt).toEpochSecond();

        long diffOnMillis = (epochEnd - epochStart);
        int jump = (int) (diffOnMillis / MILLIS_IN_A_DAY);

        long currentTime = epochStart;
        List<HistoryInfoDTO> toReturn = new ArrayList<>(jump);
        for (int i = 0; i < diffOnMillis; i++) {
            toReturn.set(i, new HistoryInfoDTO(0.0, currentTime));
            currentTime += MILLIS_IN_A_DAY;
        }

        return toReturn;
    }

}
