package com.wss.webservicestudy.web.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.entity.FeedMeet;
import com.wss.webservicestudy.web.user.type.LoginType;
import com.wss.webservicestudy.web.user.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

//    @JsonManagedReference //참조가 되는 앞부분을 의미하며, 정상적으로 직렬화를 수행한다.
    @OneToMany(mappedBy = "writer")
    private List<Feed> feeds = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<FeedMeet> feedMeets = new ArrayList<>();

    @Builder
    public User(Long id, String name, String email, LoginType loginType, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.loginType = loginType;
        this.role = role;
    }
}
