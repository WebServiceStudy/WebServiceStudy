package com.wss.webservicestudy.web.user.entity;

import com.wss.webservicestudy.web.common.entity.BaseEntity;
import com.wss.webservicestudy.web.user.dto.UserRespDto;
import com.wss.webservicestudy.web.user.type.Gender;
import com.wss.webservicestudy.web.user.type.LoginType;
import com.wss.webservicestudy.web.user.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String birthday;

    private String tel1;

    private String tel2;

    private String tel3;

    @Builder
    public User(Long id, String name, String email, String password, LoginType loginType, Role role, Gender gender, String birthday, String tel1, String tel2, String tel3) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.loginType = loginType;
        this.role = role;
        this.gender = gender;
        this.birthday = birthday;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.tel3 = tel3;
    }

    public User() {}

    public UserRespDto toDto() {
        return UserRespDto.builder()
                .id(this.getId())
                .email(this.getEmail())
                .birthday(this.getBirthday())
                .loginType(this.getLoginType())
                .tel1(this.getTel1())
                .tel2(this.getTel2())
                .tel3(this.getTel3())
                .role(this.getRole())
                .build();
    }
}
