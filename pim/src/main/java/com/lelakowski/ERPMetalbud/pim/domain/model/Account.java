package com.lelakowski.ERPMetalbud.pim.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "externalName", nullable = false, unique = true)
    private String externalName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "privileges_id")
    private Privileges privileges;


    public Account(String externalName, String username, String password, String email, Privileges privileges) {
        this.externalName = externalName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.privileges = privileges;
    }
}
