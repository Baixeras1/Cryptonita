package com.cryptonita.app.data.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = "wallets")
public class AccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "User_ID")
    private UserModel user;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    @MapKeyJoinColumn(name = "id")
    private Map<CoinModel, WalletModel> wallets = new HashMap<>();

    @Builder
    public AccountModel(UserModel user){
        this.user = user;
    }

}
