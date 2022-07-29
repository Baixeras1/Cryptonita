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

    @Column(unique = true)
    private String userId;


    @OneToMany(mappedBy = "walletId")
    private List<WalletModel> walletModels;

    @Builder
    public AccountModel(long id,String userId){
        this.id = id;
        this.userId = userId;
    }

}
