package com.cryptonita.app.data.entities;

import com.cryptonita.app.data.entities.enums.UserRole;
import com.cryptonita.app.data.entities.enums.UserType;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "username"})
@ToString(exclude = {"account", "favourites"})
@Table(name = "USERS")
@Valid
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "El campo email no puede estar vacio")
    @Email
    @Column(unique = true)
    private String mail;

    @NotEmpty(message = "El campo nombre de ususario no puede ir vacio")
    @Column(unique = true)
    private String username;

    @Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,12}$",
            message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToOne(mappedBy = "user")
    private AccountModel account;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<FavouritesModel> favourites = new ArrayList<>();

    @Builder
    public UserModel(String mail, String username, String password, UserRole role, UserType type) {
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.role = role;
        this.type = type;
    }

}
