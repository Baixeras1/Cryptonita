package com.cryptonita.app.data.entities;

import lombok.*;
import org.h2.engine.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Create History JPA entity
 * */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
@EqualsAndHashCode(of = {"id","user"})
@Table(name = "HISTORY")
public class HistoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private UserModel user;
    private LocalDateTime date;
    private String origin;
    private String destiny;
    private double quantity;

    @Builder
    public HistoryModel(UserModel user,LocalDateTime date,String origin,String destiny,double quantity) {
        this.user = user;
        this.date = date;
        this.origin = origin;
        this.destiny = destiny;
        this.quantity = quantity;
    }



}
