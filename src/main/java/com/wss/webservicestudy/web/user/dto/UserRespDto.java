package com.wss.webservicestudy.web.user.dto;

import com.wss.webservicestudy.web.user.type.Gender;
import com.wss.webservicestudy.web.user.type.LoginType;
import com.wss.webservicestudy.web.user.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRespDto {
    private Long id;

    private String name;

    private String email;

    private LoginType loginType;

    private Role role;

    private Gender gender;

    private String birthday;

    private String tel1;

    private String tel2;

    private String tel3;

    @Builder
    public UserRespDto(Long id, String name, String email, LoginType loginType, Role role, Gender gender, String birthday, String tel1, String tel2, String tel3) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.loginType = loginType;
        this.role = role;
        this.gender = gender;
        this.birthday = birthday;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.tel3 = tel3;
    }

}
