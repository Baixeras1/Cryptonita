package com.cryptonita.app.data.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    private List<WalletModel> wallets = new ArrayList<>();

    @Builder
    public AccountModel(UserModel user){
        this.user = user;
    }

}
