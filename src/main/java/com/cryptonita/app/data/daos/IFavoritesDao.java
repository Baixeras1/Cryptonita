package com.cryptonita.app.data.daos;

import com.cryptonita.app.data.entities.FavouritesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IFavoritesDao extends JpaRepository<FavouritesModel,Long> {

    Optional<FavouritesModel> findById(long id);

    List<FavouritesModel> findAll();

    List<FavouritesModel> findByUser_Username(String name);

    Optional<FavouritesModel> deleteById(long id);

}
