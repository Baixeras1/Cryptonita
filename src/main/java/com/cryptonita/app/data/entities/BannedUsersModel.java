package com.cryptonita.app.data.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"id", "user"})
@Table(name = "BannedUsers")
public class BannedUsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private UserModel user;

    private LocalDate bannedAt;

    private LocalDate expiresAt;

    @Builder
    public BannedUsersModel(LocalDate bannedAt, LocalDate expiresAt, UserModel user) {
        this.bannedAt = bannedAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }

}
