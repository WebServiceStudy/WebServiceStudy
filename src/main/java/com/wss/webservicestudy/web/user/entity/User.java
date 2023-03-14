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
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    private String birthday;

//    @JsonManagedReference //참조가 되는 앞부분을 의미하며, 정상적으로 직렬화를 수행한다.
    @OneToMany(mappedBy = "writer")
    private List<Feed> feeds = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<FeedMeet> feedMeets = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String isWritable; //nickname, birthday, gender 필수

    private boolean isInfo;

    @Builder
    public User(Long id, String nickname, String email, String password, LoginType loginType, Role role, String birthday, List<Feed> feeds, List<FeedMeet> feedMeets, Gender gender, String isWritable, boolean isInfo) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.loginType = loginType;
        this.role = role;
        this.birthday = birthday;
        this.feeds = feeds;
        this.feedMeets = feedMeets;
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
}
