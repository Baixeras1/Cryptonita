package com.cryptonita.app.data.daos;

import com.cryptonita.app.data.entities.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface IAccountDao extends JpaRepository<AccountModel,Long> {

    Optional<AccountModel> findByUserUsername(String userName);
    Optional<AccountModel> findByUser_Id(long id);
}
