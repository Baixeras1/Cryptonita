package com.cryptonita.app.data.entities;

import lombok.*;
import org.h2.engine.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"id"})
@ToString(exclude = "user")
@Table(name = "Favourites")
public class FavouritesModel {

    @Id
    private long id;

    @ManyToOne
    private UserModel user;

    @ManyToOne
    private CoinModel coin;



}



