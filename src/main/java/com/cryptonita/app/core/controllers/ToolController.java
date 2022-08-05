package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IConvertorService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/api/tools")
@AllArgsConstructor
public class ToolController {

    private final IConvertorService convertorService;

    @RequestMapping("/convert")
    public Mono<RestResponse> convert(String from, @RequestParam(required = false) Optional<String> to, double amount) {
        return to.map(s -> convertorService.convert(from, s, amount)
                        .map(RestResponse::encapsulate)
                )
                .orElseGet(() -> convertorService.convert(from, amount)
                        .map(RestResponse::encapsulate)
                );

    }

}
