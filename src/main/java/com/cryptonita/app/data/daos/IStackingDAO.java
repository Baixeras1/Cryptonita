package com.cryptonita.app.data.daos;

import com.cryptonita.app.data.entities.StackingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStackingDAO extends JpaRepository<StackingModel, Long> {


}
