package com.wss.webservicestudy.web.user.entity;

import com.wss.webservicestudy.web.common.entity.BaseEntity;
import com.wss.webservicestudy.web.user.dto.UserRespDto;
import com.wss.webservicestudy.web.user.type.Gender;
import com.wss.webservicestudy.web.user.type.LoginType;
import com.wss.webservicestudy.web.user.type.Role;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@DynamicUpdate
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String profile;

    private String birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String isWritable; //nickname, birthday, gender 필수

    private boolean isInfo;

    @Builder
    public User(Long id, String nickname, String email, String password, LoginType loginType, Role role, String profile, String birthday, Gender gender, String isWritable, boolean isInfo) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.loginType = loginType;
        this.role = role;
        this.profile = profile;
        this.birthday = birthday;
        this.gender = gender;
        this.isWritable = isWritable;
        this.isInfo = isInfo;
    }



    public User() {}

    public UserRespDto toDto() {
        return UserRespDto.builder()
                .id(this.getId())
                .email(this.getEmail())
                .nickname(this.getNickname())
                .birthday(this.getBirthday())
                .loginType(this.getLoginType())
                .role(this.getRole())
                .isInfo(this.isInfo())
                .profile(this.getProfile())
                .build();
    }

    public int getAge() {
        LocalDate now = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(this.birthday, DateTimeFormatter.ofPattern("yyyyMMdd"));
        int age = now.minusYears(birthDate.getYear()).getYear();
        if (birthDate.plusYears(age).isAfter(now)) {
            return age - 1;
        }
        return age;
    }

    public void checkIsWritable(){
        if(!isWritable()) throw new IllegalArgumentException("쓰기 권한 없음");
    }

    public boolean isWritable(){
        return this.isWritable.equals("y");
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile(String imgurl) {
        this.profile = imgurl;
    }
}
