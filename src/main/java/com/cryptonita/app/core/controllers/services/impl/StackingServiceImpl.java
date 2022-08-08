package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.core.controllers.services.IStackingService;
import com.cryptonita.app.data.providers.IRegisterProvider;
import com.cryptonita.app.data.providers.IStackingProvider;
import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.dto.data.response.StackingDTO;
import com.cryptonita.app.dto.request.RegisterRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class StackingServiceImpl implements IStackingService {

    private final IUserProvider userProvider;
    private final IRegisterProvider registerProvider;

    private final IStackingProvider stackingProvider;


    /**
     * Este metodo muestra una lista de todos los stakes
     * @return Lista de stakes
     */
    @Override
    public List<StackingDTO> findAll() {
        return stackingProvider.findAll();
    }

    /**
     * Este metodo muestra todos los stakes de un usuario
     * @param username nombre del usuario
     * @return todos los stakes del usuario
     */
    @Override
    public List<StackingDTO> findAllByUser(String username) {
        return stackingProvider.getAllUserStakes(username); //TODO Security
    }

    /**
     * Este metodo hace un Stake para un usuario
     * @param username nombre del usuario
     * @param coinName nombre de la moneda
     * @param quantity cantidad de dicha moneda
     * @param daysToExpires dias para que expire el stake
     * @return el stake creado
     */
    @Override
    public StackingDTO stake(String username, String coinName, double quantity, int daysToExpires) {

        StackingDTO stackingDTO = stackingProvider.stake(username,coinName,quantity,daysToExpires);

        RegisterRequestDTO registerResponseDTO = RegisterRequestDTO.builder()
                .user(username)
                .date(LocalDate.now())
                .quantity(quantity)
                .destiny("Staking id: " + String.valueOf(stackingDTO.getId())) //TODO Security
                .origin("Wallet id: " + stackingDTO.getUser().username) //TODO Security & MAP
                .build();

        //registerProvider.log(registerResponseDTO);

        return stackingDTO; //TODO Security
    }

    /**
     * Este metodo borra un stake por su ID
     * @param id del stake
     * @param username nombre del usuario que tiene el stake
     * @return el stake que se ha borrado
     */
    @Override
    public StackingDTO unStake(long id, String username) {

        StackingDTO dto = stackingProvider.unStake(id,username);

        RegisterRequestDTO registerResponseDTO = RegisterRequestDTO.builder()
                .user(username)
                .origin("Staking id: " + dto.getId())
                .destiny("Wallet id: " + dto.getUser().username) //TODO MAP
                .build();

        //registerProvider.log(registerResponseDTO);

        return dto; //TODO Security
    }

    /**
     * Este metodo busca un stake de un usuario
     * @param id del stake
     * @param username nombre del usuario
     * @return el stake buscado
     */
    @Override
    public StackingDTO findUserStakeById(long id, String username) {

        return stackingProvider.getUserStake(id,username); //TODO Security
    }
}
