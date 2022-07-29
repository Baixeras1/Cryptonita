package com.cryptonita.app.data.daos;

import com.cryptonita.app.data.entities.StackingModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStackingDAO extends JpaRepository <StackingModel,Long> {

    StackingModel findByUserId(long id);

    List<StackingModel> findAllByUserId(long id);

}
