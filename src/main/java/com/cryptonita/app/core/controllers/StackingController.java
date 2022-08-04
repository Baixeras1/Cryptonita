package com.cryptonita.app.core.controllers;


import com.cryptonita.app.core.controllers.services.IStackingService;
import com.cryptonita.app.dto.response.StackingDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/stacking")
public class StackingController {

    private final IStackingService stackingService;


    @GetMapping("/all")
    public List<StackingDTO> getAll(){ return stackingService.findAll();}

    @GetMapping("/allUser")
    public List<StackingDTO> getAllById(String username){return stackingService.findAllByUser(username);}

    @PostMapping("/stake")
    public StackingDTO stake(String username,String coinName,double quantity,int daysToExpire){
        return stackingService.stake(username,coinName,quantity,daysToExpire);
    }

    @DeleteMapping("/unStake")
    public StackingDTO unStake(long id,String username){
        return stackingService.unStake(id,username);
    }


}
