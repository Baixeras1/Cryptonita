package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IConvertorService;
import com.cryptonita.app.core.controllers.services.IPorfolioService;
import com.cryptonita.app.data.providers.IAccountProvider;
import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.data.providers.mappers.IMapper;
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
    private final IMapper<Map<String,WalletResponseDto>, PorfolioResponseDTO> mapper;

    @Override
    public WalletResponseDto get(String coin) {
        return acountProvider.get(securityContextHelper.getUser().getUsername(),coin);
    }

    @Override
    public PorfolioResponseDTO getAll() {
        UserResponseDTO userResponseDTO = securityContextHelper.getUser();
        Map<String,WalletResponseDto> wallets = userResponseDTO.getWallet();

        return mapper.mapToDto(wallets);
    }



}
