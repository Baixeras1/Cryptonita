package com.cryptonita.app.data.daos;

import com.cryptonita.app.data.entities.CoinModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICoinDAO extends JpaRepository<CoinModel,Long> {

    Optional<CoinModel> findByName(String name);

    void deleteByName(String name);

}
