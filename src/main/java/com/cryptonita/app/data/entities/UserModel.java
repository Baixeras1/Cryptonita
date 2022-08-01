package com.cryptonita.app.data.entities;

import com.cryptonita.app.data.entities.enums.UserRole;
import com.cryptonita.app.data.entities.enums.UserType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "username"})
@ToString(exclude = {"account", "favourites"})
@Table(name = "USERS")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String mail;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToOne(mappedBy = "user")
    private AccountModel account;

    @OneToMany(mappedBy = "user")
    private List<FavouritesModel> favourites;

    @Builder
    public UserModel(String mail, String username, String password, UserRole role, UserType type) {
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.role = role;
        this.type = type;
    }

}
