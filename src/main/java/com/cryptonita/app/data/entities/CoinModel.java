package com.cryptonita.app.data.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CoinModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private int rank;

    @Builder
    public CoinModel(String name,int rank){
        this.name = name;
        this.rank = rank;
    }


}
