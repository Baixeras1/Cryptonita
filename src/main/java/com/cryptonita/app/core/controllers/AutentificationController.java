package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IAutentificationService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import com.cryptonita.app.dto.data.request.UserRegisterDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/Autentification")
public class AutentificationController {
    private final IAutentificationService autentificationService;

    @GetMapping("/register")
    public RestResponse register(UserRegisterDTO userRegisterDTO){
        return RestResponse.encapsulate(autentificationService.register(userRegisterDTO));
    }

}
