package com.cryptonita.app.data.daos;

import com.cryptonita.app.data.entities.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface IAccountDao extends JpaRepository<AccountModel,Long> {

    AccountModel findByUserUsername(String userName);
    AccountModel findByUser_Id(long id);
}
