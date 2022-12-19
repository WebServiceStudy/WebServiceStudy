package com.wss.webservicestudy.web.feed.dto;

import com.wss.webservicestudy.web.common.security.domain.PrincipalDetail;
import com.wss.webservicestudy.web.feed.entity.Feed;
import com.wss.webservicestudy.web.feed.type.FeedStatus;
import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.repository.UserRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
public class CreateFeedDto {
    @Builder
    public CreateFeedDto(String title, String content, String addr, String latitude, String longitude, int maxUser, int minAge, int maxAge) {
        this.title = title;
        this.content = content;
        this.addr = addr;
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxUser = maxUser;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    // 제목
    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;

    // 작성자
    private User writer;

    // 내용
    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;

    // 모집 장소
    @NotBlank(message = "주소는 필수 입력값입니다.")
    private String addr;

    // 위도
    private String latitude;

    // 경도
    private String longitude;

    // 최대인원
    private int maxUser;

    // 나이제한
    @PositiveOrZero(message = "최소 나이는 음수가 될 수 없습니다.")
    private int minAge;
    @PositiveOrZero(message = "최대 나이는 음수가 될 수 없습니다.")
    private int maxAge;

    public Feed toEntity(){
        return Feed.builder()
                .writer(writer)//현재 로그인한 사람으로 바꿔야함
                .title(title)
                .content(content)
                .status(FeedStatus.RECRUITING)
                .addr(addr)
                .latitude(latitude)
                .longitude(longitude)
                .maxUser(maxUser)
                .minAge(minAge)
                .maxAge(maxAge)
                .build();
    }
}
