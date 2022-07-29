package com.cryptonita.app.data.daos;

import com.cryptonita.app.data.entities.WalletModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Wallet repository
 * */
public interface IWalletDao extends JpaRepository <WalletModel , Long> {

    WalletModel findById(long id);
    List<WalletModel> findAll();

    WalletModel deleteById(long id);



}
