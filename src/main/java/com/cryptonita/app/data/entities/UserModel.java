package com.cryptonita.app.data.entities;

import com.cryptonita.app.data.entities.enums.UserRole;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Table(name = "USERS")
public class UserModel {

    @Id
    private long id;

    @Column(unique = true)
    private String mail;

    @Column(unique = true)
    private String username;

    private String password;

    private UserRole role;

    @Builder
    public UserModel(String mail, String username, String password, UserRole role) {
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
