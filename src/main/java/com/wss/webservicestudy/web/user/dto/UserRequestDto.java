package com.wss.webservicestudy.web.user.dto;

import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.type.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserRequestDto {
    private Long id;

    private String nickname;

    private String email;

    private String birthday;

    private Gender gender;
    
    public UserRequestDto() {
    }

    @Builder
    public UserRequestDto(Long id, String nickname, String email, String birthday, Gender gender) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
    }

    public User toUser() {
        return User.builder()
                .id(this.id)
                .email(this.email)
                .gender(this.gender)
                .nickname(this.nickname)
                .birthday(this.birthday)
                .build();
    }
}
