package com.wss.webservicestudy.web.feed.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wss.webservicestudy.web.feed.dto.UpdateFeedDto;
import com.wss.webservicestudy.web.feed.type.FeedStatus;
import com.wss.webservicestudy.web.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Feed {
    // 피드 식별값
    @Id
    @Column(name = "FEED_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 작성자 식별값
//    @JsonBackReference // 참조가 되는 뒷부분을 의미하며, 직렬화를 수행하지 않는다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer", referencedColumnName = "id")
    private User writer;

    // 일대다 관계 - 참여자
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<FeedMeet> feedMeets = new ArrayList<>();

    // 피드 제목
    @Column(nullable = false)
    private String title;

    // 내용
    @Column(nullable = false)
    @Lob
    private String content;

    // 상태값
    @Enumerated(EnumType.STRING)
    private FeedStatus status;

    // 모집일
    @Column(nullable = false)
    private LocalDateTime date;

    // 모임 장소
    @Column(nullable = false)
    private String addr;

    // 위도
    private String latitude;

    // 경도
    private String longitude;

    // 최대인원
    private int maxUser;

    // 나이제한
    private int minAge;
    private int maxAge;

    // 남자참여수
    private int curMale;

    // 여자참여수
    private int curFemale;

    // 개설일
    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedDate;

//    public void addFeedMeet(FeedMeet feedMeet) {
//        this.feedMeets.add(feedMeet);
//        feedMeet.setFeed(this);
//    }

    public void setWriter(User user){
        this.writer = user;
        user.getFeeds().add(this);
    }

    public Feed update(UpdateFeedDto updateFeedDto){
        this.title = updateFeedDto.getTitle();
        this.content = updateFeedDto.getContent();
        this.date = updateFeedDto.getDate();
        this.addr = updateFeedDto.getAddr();
        this.latitude = updateFeedDto.getLatitude();
        this.longitude = updateFeedDto.getLongitude();
        this.maxUser = updateFeedDto.getMaxUser();
        this.minAge = updateFeedDto.getMinAge();
        return this;
    }

    @Builder
    public Feed(User writer, String title, String content, FeedStatus status, LocalDateTime date, String addr, String latitude, String longitude, int maxUser, int minAge, int maxAge) {
        setWriter(writer);
        this.title = title;
        this.content = content;
        this.status = status;
        this.date = date;
        this.addr = addr;
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxUser = maxUser;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }
}
