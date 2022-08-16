package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IConvertorService;
import com.cryptonita.app.core.controllers.services.IPorfolioService;
import com.cryptonita.app.data.providers.IAccountProvider;
import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.dto.data.response.CoinDetailsDTO;
import com.cryptonita.app.dto.data.response.PorfolioResponseDTO;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import com.cryptonita.app.dto.data.response.WalletResponseDto;
import com.cryptonita.app.integration.services.ICoinMarketService;
import com.cryptonita.app.security.SecurityContextHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PorfolioServiceImpl implements IPorfolioService {

    private IUserProvider userProvider;
    private final IAccountProvider acountProvider;
    private final ICoinMarketService marketService;
    private final SecurityContextHelper securityContextHelper;

    private final IConvertorService convertorService;

    @Override
    public WalletResponseDto get(String coin) {
        return acountProvider.get(securityContextHelper.getUser().getUsername(),coin);
    }

    @Override
    public List<WalletResponseDto> getAll() {
        return acountProvider.getAllFromUser(securityContextHelper.getUser().getUsername());
    }

    @Override
    public PorfolioResponseDTO getPorfolio() {
        UserResponseDTO userResponseDTO = securityContextHelper.getUser();
        Map<String,WalletResponseDto> walletResponseDtos = userResponseDTO.getWallet();

        List<CoinDetailsDTO> coinDetailsDTOList = walletResponseDtos.values().stream()
                .map(this::mapToDetails)
                .collect(Collectors.toList());

        double totalBalance = calculateBalance(coinDetailsDTOList);

        calculateAllocation(coinDetailsDTOList, totalBalance);

        return PorfolioResponseDTO.builder()
                .balance(totalBalance)
                .coinDetailsDTOList(coinDetailsDTOList)
                .build();
    }

    private CoinDetailsDTO mapToDetails(WalletResponseDto dto) {
        return CoinDetailsDTO.builder()
                .id(dto.getId())
                .name(dto.getCoinName())
                .quantity(dto.getQuantity())
                .coinMarketDTO(marketService.getCoinMetadataByName(dto.getCoinName()).block())
                .build();
    }

    public double calculateBalance(List<CoinDetailsDTO> coinDetailsDTOList) {
        return coinDetailsDTOList.stream()
                .map(coinDetailsDTO -> coinDetailsDTO.getQuantity() * coinDetailsDTO.getCoinMarketDTO().priceUsd)
                .reduce(Double::sum)
                .orElse(0.0);

    }

    private void calculateAllocation(List<CoinDetailsDTO> coinDetailsDTOList, double totalBalance) {
        for (CoinDetailsDTO coinDetailsDTO : coinDetailsDTOList) {
            double individualPrice = coinDetailsDTO.getQuantity() * coinDetailsDTO.getCoinMarketDTO().priceUsd;
            double allocation = (individualPrice / totalBalance) * 100;

            coinDetailsDTO.setAllocation(allocation);
        }
    }


}
