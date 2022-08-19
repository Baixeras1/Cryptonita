package com.cryptonita.app.security.filters;

import com.cryptonita.app.security.SecurityContextHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RatePerMonthFilter {

    private final RatePerMonthFilter ratePerMonthFilter;
    private final SecurityContextHelper securityContextHelper;

}
