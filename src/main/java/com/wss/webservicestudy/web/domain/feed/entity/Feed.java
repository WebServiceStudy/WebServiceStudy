package com.wss.webservicestudy.web.domain.feed.entity;

import com.wss.webservicestudy.web.common.entity.Category;
import com.wss.webservicestudy.web.common.enums.Status;
import com.wss.webservicestudy.web.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@Entity
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedId;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false)
    private int maleMax;

    @Column(nullable = false)
    private int femaleMax;

    @Column(nullable = false)
    private int nowMaleNum;

    @Column(nullable = false)
    private int nowFemaleNum;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;

    private String meetAdrOne;

    private String meetAdrTwo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @JoinColumn(name = "cate_id")
    private Category category;

    private LocalDateTime meetTime;

    public Feed() {}
    @Builder
    public Feed(Long feedId, String title, int maleMax, int femaleMax, int nowMaleNum, int nowFemaleNum, User user, String meetAdrOne, String meetAdrTwo, String content, Status status, Category category, LocalDateTime meetTime) {
        this.feedId = feedId;
        this.title = title;
        this.maleMax = maleMax;
        this.femaleMax = femaleMax;
        this.nowMaleNum = nowMaleNum;
        this.nowFemaleNum = nowFemaleNum;
        this.user = user;
        this.meetAdrOne = meetAdrOne;
        this.meetAdrTwo = meetAdrTwo;
        this.content = content;
        this.status = status;
        this.category = category;
        this.meetTime = meetTime;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
