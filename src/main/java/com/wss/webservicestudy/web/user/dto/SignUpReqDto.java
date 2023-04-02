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

import javax.validation.constraints.NotBlank;

/**
 * 회원가입 요청 dto
 */
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

    private String birthday;
    @NotBlank(message = "성별은 필수 값입니다.")
    private Gender gender;


    @Builder
    public SignUpReqDto(String nickname, String email, String password, Role role, String birthday, Gender gender) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
    }

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(getEmail())
                .password(passwordEncoder.encode(password))
                .nickname(getNickname())
                .gender(getGender())
                .birthday(getBirthday())
                .build();
    }
}
