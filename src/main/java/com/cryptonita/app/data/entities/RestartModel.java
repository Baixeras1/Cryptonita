package com.cryptonita.app.data.entities;

import lombok.*;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;


@Entity
@Data
@Table(name = "Restart")
public class RestartModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Months")
    private String month;

    @Column(name = "Years")
    private String year;

    private boolean restart;
}
