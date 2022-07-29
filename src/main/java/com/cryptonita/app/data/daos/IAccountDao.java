package com.cryptonita.app.data.daos;

import com.cryptonita.app.data.entities.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface IAccountDao extends JpaRepository<AccountModel,Long> {

    AccountModel findById(long id);

    AccountModel findByUserModel();

    List<AccountModel>findAll();

    void deleteById(long id);
}
