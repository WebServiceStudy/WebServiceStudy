package com.wss.webservicestudy.web.feed.entity;

import com.wss.webservicestudy.web.category.entity.Category;
import com.wss.webservicestudy.web.common.entity.BaseEntity;
import com.wss.webservicestudy.web.feed.dto.UpdateFeedDto;
import com.wss.webservicestudy.web.feed.type.FeedDeleteYn;
import com.wss.webservicestudy.web.feed.type.FeedStatus;
import com.wss.webservicestudy.web.feed.type.MeetingType;
import com.wss.webservicestudy.web.user.entity.User;
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
    private final List<FeedMeet> feedMeets = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", referencedColumnName = "id")
    private Category category;

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

    // 남녀 구분 유무
    private boolean genderDivisionYn;

    // 삭제여부
    @Enumerated(EnumType.STRING)
    private FeedDeleteYn deleteYn;



    public void setWriter(User user){
        this.writer = user;
    }

    public void setStatus(FeedStatus status) {
        this.status = status;
    }

    // TODO : FeedDeleteYn 제대로 들어가는 게 맞는지 확인 필요
    public void setDeleteYn(FeedDeleteYn feedDeleteYn) {
        this.deleteYn = feedDeleteYn;
    }

    public Long getWriterId(){
        return this.writer.getId();
    }

    public String getWriterName(){
        return this.writer.getNickname();
    }



    public void addParticipant(User user) {
        availableToAdd(user);

        if (user.isMale()) {
            addCurMale();
            return;
        }
        addCurFemale();
    }

    public void deductParticipant(User user){
        if (user.isMale()) {
            deductCurMale();
            return;
        }
        deductCurFemale();
    }

    private void addCurMale() {
        this.curMale++;
    }

    private void addCurFemale() {
        this.curFemale++;
    }

    private void deductCurMale(){
        this.curMale--;
    }

    private void deductCurFemale() {
        this.curFemale--;
    }

    public void addViews(){
        this.views++;
    }

    public boolean isFeedWriter(User user) {
        if(user == null){
            return false;
        }
        return this.writer.getId().equals(user.getId());
    }

    public boolean existsParticipant() {
        return this.curFemale + this.curMale > 1; // 작성자 제외
    }

    public void availableToAdd(User user) {
        // 인원수 체크
        if(this.genderDivisionYn
            ? (user.isMale() ? this.maxMale == this.curMale : this.maxFemale == this.curFemale)
            : (this.maxUser == this.curMale + this.curFemale)){
            throw new IllegalArgumentException("인원 수를 확인해 주세요.");
        }
    }

    public boolean isAvailableAge(int age){
        return age >= this.minAge
                && age <= this.maxAge;
    }



    // TODO : Mapper 삭제 후 방식 통일
    @Builder
    public Feed(User writer, String title, String content, FeedStatus status, LocalDateTime date, String addr, String latitude, String longitude, int maxUser, int maxMale, int maxFemale, int minAge, int maxAge, boolean genderDivisionYn) {
        setWriter(writer);
        this.title = title;
        this.content = content;
        this.status = status;
        this.date = date;
        this.addr = addr;
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxUser = maxUser;
        this.maxMale = maxMale;
        this.maxFemale = maxFemale;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.genderDivisionYn = genderDivisionYn;
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
}
