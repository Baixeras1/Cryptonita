package com.cryptonita.app.data.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "BannedUsers")
public class BannedUsersModel {

    @Id
    private long userID;

    private LocalDate bannedAt;

    private LocalDate expiresAt;

    @MapsId
    @OneToOne
    private UserModel user;

}
