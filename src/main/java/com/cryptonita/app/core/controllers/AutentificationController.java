package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IAutentificationService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import com.cryptonita.app.data.entities.enums.UserRole;
import com.cryptonita.app.data.entities.enums.UserType;
import com.cryptonita.app.dto.data.request.UserRegisterDTO;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/autentication")
public class AutentificationController {
    private final IAutentificationService autentificationService;

    @PostMapping ("/register")
    public RestResponse register(String mail, String username,String password){

        return RestResponse.encapsulate(autentificationService.register(
                UserRegisterDTO.builder()
                        .username(username)
                        .mail(mail)
                        .password(password)
                        .role(UserRole.USER)
                        .type(UserType.FREE)
                        .build()

        ));
    }

}
