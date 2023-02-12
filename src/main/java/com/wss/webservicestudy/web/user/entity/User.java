package com.wss.webservicestudy.web.user.entity;

import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.common.entity.BaseEntity;
import com.wss.webservicestudy.web.user.dto.UserRespDto;
import com.wss.webservicestudy.web.user.type.Gender;
import com.wss.webservicestudy.web.user.type.LoginType;
import com.wss.webservicestudy.web.user.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    private String birthday;

//    @JsonManagedReference //참조가 되는 앞부분을 의미하며, 정상적으로 직렬화를 수행한다.
    @OneToMany(mappedBy = "writer")
    private List<Feed> feeds = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<FeedMeet> feedMeets = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String tel1;

    private String tel2;

    private String tel3;

    private String isWritable; //nickname, birthday, gender 필수

    @Builder
    public User(Long id, String nickname, String email, String password, LoginType loginType, Role role, Gender gender, String birthday, String tel1, String tel2, String tel3, String isWritable) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.loginType = loginType;
        this.role = role;
        this.gender = gender;
        this.birthday = birthday;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.tel3 = tel3;
        this.isWritable = isWritable;
    }

    public User() {}

    public UserRespDto toDto() {
        return UserRespDto.builder()
                .id(this.getId())
                .email(this.getEmail())
                .nickname(this.getNickname())
                .birthday(this.getBirthday())
                .loginType(this.getLoginType())
                .tel1(this.getTel1())
                .tel2(this.getTel2())
                .tel3(this.getTel3())
                .role(this.getRole())
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
}
