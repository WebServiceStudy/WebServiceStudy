package com.wss.webservicestudy.web.user.dto;

import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.type.Gender;
import com.wss.webservicestudy.web.user.type.LoginType;
import com.wss.webservicestudy.web.user.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class SignUpReqDto {
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    private LoginType loginType;

    private Role role;
    private String birthday;
    @NotBlank(message = "성별은 필수 값입니다.")
    private Gender gender;

    private String tel1;

    private String tel2;

    private String tel3;

    @Builder
    public SignUpReqDto(String nickname, String email, String password, LoginType loginType, Role role, String birthday, Gender gender, String tel1, String tel2, String tel3) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.loginType = loginType;
        this.birthday = birthday;
        this.gender = gender;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.tel3 = tel3;
    }

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(getEmail())
                .password(passwordEncoder.encode(password))
                .nickname(getNickname())
                .gender(getGender())
                .birthday(getBirthday())
                .tel1(getTel1())
                .tel2(getTel2())
                .tel3(getTel3())
                .build();
    }
}
