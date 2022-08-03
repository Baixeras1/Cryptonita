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
@Table(name = "COINS")
public class CoinModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private int rank;

    @Column(unique = true)
    private String symbol;

    @Builder
    public CoinModel(String name,int rank){
        this.name = name;
        this.rank = rank;
    }


}
