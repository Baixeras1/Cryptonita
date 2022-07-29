package com.cryptonita.app.data.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of= {"id","name"})
@ToString(exclude = "CoinModel")
public class CoinModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;

    public CoinModel(long id,String name){
        this.id = id;
        this.name = name;
    }


}
