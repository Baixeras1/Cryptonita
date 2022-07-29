package com.cryptonita.app.data.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Create Stacking JPA Entity, this class is relathionship whit UserModel and CoinModel
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class StackingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Coin_ID")
    @ManyToOne
    private CoinModel coinId;

    @Column(name="Created_At")
    private LocalDateTime createdAt;

    @Column(name="Days_to_expire")
    private int daysToExpire;

    @Column(name="Quantity")
    private double quantity;

    @Column(name = "UserId")
    @ManyToOne
    private UserModel userId;

    @Builder
    public StackingModel(CoinModel coinId, LocalDateTime createdAt, int daysToExpire, double quantity, UserModel userId) {
        this.coinId = coinId;
        this.createdAt = createdAt;
        this.daysToExpire = daysToExpire;
        this.quantity = quantity;
        this.userId = userId;
    }
}
