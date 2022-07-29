package com.cryptonita.app.data.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "WALLET")
public class WalletModel {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private AccountModel account;

    @ManyToOne
    private CoinsModel coin;

    private float quantity;

    @Builder
    public WalletModel(AccountModel account, CoinsModel coin, float quantity) {
        this.account = account;
        this.coin = coin;
        this.quantity = quantity;
    }
}
