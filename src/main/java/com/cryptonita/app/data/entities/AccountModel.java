package com.cryptonita.app.data.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "userId"})
@ToString(exclude = "accountModel")
public class AccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "User_ID")
    private UserModel user;

    @OneToMany(mappedBy = "account")
    private List<WalletModel> walletModels;

    @Builder
    public AccountModel(long id,UserModel user){
        this.id = id;
        this.user = user;
    }

}
