package com.cryptonita.app.data.daos;

import com.cryptonita.app.data.entities.HistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * History repository
 * */

public interface HistoryDao extends JpaRepository<HistoryModel,Long> {

    HistoryModel findById(long id);

    List<HistoryModel> findAll();

    List<HistoryModel> findAllByUser_Id(long id);

    HistoryModel deleteById(long id);




}
