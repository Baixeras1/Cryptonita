package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IAdminService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import com.cryptonita.app.dto.data.response.BannedUserResponseDTO;
import com.cryptonita.app.dto.data.response.CoinResponseDTO;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import com.cryptonita.app.security.SecurityContextHelper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final IAdminService adminService;
    private final SecurityContextHelper securityContextHelper;

    @PostMapping("/assets/create")
    public CoinResponseDTO createCoin(String name,String symbol,int rank){
        return adminService.createCoin(name,symbol,rank);
    }

    @DeleteMapping("/assets/delete")
    public RestResponse deleteCoin(String name){
        return RestResponse.encapsulate(adminService.deleteCoin(name));
    }

    @PostMapping("/users/ban")
    public BannedUserResponseDTO banUser (String mail){
        return adminService.banUser(mail);
    }

    @PostMapping("/users/unBan")
    public BannedUserResponseDTO unBanUser(String mail){
        return adminService.unBanUser(mail);
    }

    @GetMapping("/users/get{id}")
    public UserResponseDTO getUserById(long id){
        return adminService.getUserById(id);
    }
}
