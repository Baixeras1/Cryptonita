package com.cryptonita.app.core.controllers;

import com.cryptonita.app.core.controllers.services.IAdminService;
import com.cryptonita.app.core.controllers.utils.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
@Tag(name = "Admin")
public class AdminController {

    private final IAdminService adminService;

    @PostMapping("/assets/create")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Creates a certain coin to be tracked")
    public RestResponse createCoin(String name,String symbol,int rank){
        return RestResponse.encapsulate(adminService.createCoin(name,symbol,rank));
    }

    @DeleteMapping("/assets/delete")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Removes a coin to be tracked")
    public RestResponse deleteCoin(String name){
        return RestResponse.encapsulate(adminService.deleteCoin(name));
    }

    @PostMapping("/users/ban")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Bans a user")
    public RestResponse banUser (String mail){
        return RestResponse.encapsulate(adminService.banUser(mail));
    }

    @PostMapping("/users/unBan")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "UnBans a user")
    public RestResponse unBanUser(String mail){
        return RestResponse.encapsulate(adminService.unBanUser(mail));
    }

    @GetMapping("/users/get{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Gets the information of a user by its id")
    public RestResponse getUserById(long id){
        return RestResponse.encapsulate(adminService.getUserById(id));
    }
}
