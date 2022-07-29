package com.cryptonita.app.data.daos;

import com.cryptonita.app.data.entities.CoinModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICoinDAO extends JpaRepository<CoinModel,Long> {

    CoinModel findByName(String name);

    CoinModel deleteByName(String name);
}
