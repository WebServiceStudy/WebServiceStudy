package com.wss.webservicestudy.web.user.dto;

import com.wss.webservicestudy.web.user.type.Gender;
import com.wss.webservicestudy.web.user.type.LoginType;
import com.wss.webservicestudy.web.user.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRespDto {
    private Long id;

    private String nickname;

    private String email;

    private LoginType loginType;

    private Role role;

    private Gender gender;

    private String birthday;

    private String profile;

    private boolean isInfo;

    @Builder
    public UserRespDto(Long id, String nickname, String email, LoginType loginType, Role role, Gender gender, String birthday, String profile, boolean isInfo) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.loginType = loginType;
        this.role = role;
        this.gender = gender;
        this.birthday = birthday;
        this.profile = profile;
        this.isInfo = isInfo;
    }
}
