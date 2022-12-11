package com.wss.webservicestudy.web.user.entity;

import com.wss.webservicestudy.web.user.type.LoginType;
import com.wss.webservicestudy.web.user.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String birthday;

    @Builder
    public User(Long id, String name, String email, LoginType loginType, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.loginType = loginType;
        this.role = role;
    }

    public User() {}


}
