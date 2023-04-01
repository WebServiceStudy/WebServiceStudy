package com.wss.webservicestudy.web.feed.entity;

import com.wss.webservicestudy.web.common.entity.BaseEntity;
import com.wss.webservicestudy.web.feed.dto.UpdateFeedDto;
import com.wss.webservicestudy.web.feed.type.FeedCategory;
import com.wss.webservicestudy.web.feed.type.FeedDeleteYn;
import com.wss.webservicestudy.web.feed.type.FeedStatus;
import com.wss.webservicestudy.web.feed.type.MeetingType;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.type.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Feed extends BaseEntity {
    // 피드 식별값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 작성자 식별값
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

    // 남자최대인원
    private int maxMale;

    // 여자최대인원
    private int maxFemale;

    // 나이제한
    private int minAge;
    private int maxAge;
    // 남자참여수
    private int curMale;

    // 여자참여수
    private int curFemale;

    // 조회수
    private int views;

    private FeedCategory category;

    // 모집 유형
    @Enumerated(EnumType.STRING)
    private MeetingType meetingType;

    // 삭제여부
    @Enumerated(EnumType.STRING)
    private FeedDeleteYn deleteYn;

    public void setWriter(User user){
        this.writer = user;
        user.getFeeds().add(this);
    }

    public void addParticipant(User user) {
        if (user.getGender().equals(Gender.MALE)) {
            addCurMale();
            return;
        }
        addCurFemale();
    }

    public void deductParticipant(User user){
        if (user.getGender().equals(Gender.MALE)) {
            deductCurMale();
            return;
        }
        deductCurFemale();
    }

    public void setStatus(FeedStatus status) {
        this.status = status;
    }

    public void setDeleteYn(FeedDeleteYn feedDeleteYn) {
        this.deleteYn = feedDeleteYn;
    }

    public void addCurMale() {
        this.curMale++;
    }

    public void addCurFemale() {
        this.curFemale++;
    }

    public void deductCurMale(){ this.curMale--;}

    public void deductCurFemale() {
        this.curFemale--;
    }

    public Long getWriterId(){
        return this.writer.getId();
    }

    public String getWriterName(){
        return this.writer.getNickname();
    }

    public void addViews(){
        this.views++;
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

    public void checkWriter(User user) {
        if (user == null) {
            throw new IllegalArgumentException("유저 정보 없음");
        }
        isFeedWriter(user.getId());
    }

    public boolean isFeedWriter(Long userId) {
        if (!this.writer.getId().equals(userId)) {
            throw new IllegalArgumentException("해당 게시글의 작성자가 아닙니다.");
        }
        return true;
    }

    public boolean existsParticipant() {
        if(this.curFemale + this.curMale > 1){ // 작성자 제외
            return true;
        }
        return false;
    }

    public void availableToAdd() {
        // 정원 체크
        if (this.maxUser == curMale + curFemale) {
            throw new IllegalArgumentException("정원 다 참");
        }
    }

    public void checkAge(int userAge) {
        // 나이체크
        if (this.minAge > userAge || this.maxAge < userAge) {
            throw new IllegalArgumentException("요구하는 나이와 다름");
        }
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
