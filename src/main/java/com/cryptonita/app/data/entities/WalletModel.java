package com.cryptonita.app.data.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id","monederoId","coinId"})
@ToString(exclude = "WalletModel")
public class WalletModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String monederoId;
    @Column(unique = true)
    private String coinId;

    private double quantity;

    @Builder
    private WalletModel(long id,String monederoId,String coinId,double quantity){
        this.id = id;
        this.monederoId = monederoId;
        this.coinId = coinId;
        this.quantity = quantity;
    }


}
