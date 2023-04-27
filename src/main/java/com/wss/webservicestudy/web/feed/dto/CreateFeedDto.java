package com.wss.webservicestudy.web.feed.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wss.webservicestudy.web.category.entity.Category;
import com.wss.webservicestudy.web.common.util.SecurityUtil;
import com.wss.webservicestudy.web.feed.type.FeedStatus;
import com.wss.webservicestudy.web.feed.valid.Mode;
import com.wss.webservicestudy.web.user.entity.User;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Mode(mode = "genderDivisionYn"
        , modeOnItems = {"maxMale", "maxFemale"}
        , modeOffItems = {"maxUser"}
        , message = "공통 모집인 경우 최대인원만을 설정하고, "
                    + "남녀 구분 모집일 경우 남/녀 최대인원만 설정합니다."
        )
public class CreateFeedDto {

    @Builder
    public CreateFeedDto(String title, String content, LocalDateTime date, String addr, String latitude, String longitude, Integer maxUser, Integer minAge, Integer maxAge, Integer maxMale, Integer maxFemale, boolean genderDivisionYn, Category category) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.addr = addr;
        this.latitude = latitude;
        this.longitude = longitude;
        this.minAge = minAge;
        this.maxAge = maxAge; // ?:: minAge <= maxAge Exception?
        this.maxUser = maxUser;
        this.maxMale = maxMale;
        this.maxFemale = maxFemale;
        this.genderDivisionYn = genderDivisionYn;
        this.category = category;
    }

    private final FeedStatus status = FeedStatus.RECRUITING;
    private final User writer = SecurityUtil.getLoginUserWithWritable();
    // 제목
    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;

    // 내용
    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;

    // 모집일
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm")
    @Future
    @NotNull(message = "모집일은 필수 입력값입니다.")
    private LocalDateTime date;

    // 모집 장소
    @NotBlank(message = "주소는 필수 입력값입니다.")
    private String addr;

    // 위도
    private String latitude;

    // 경도
    private String longitude;

    // 나이제한
    @PositiveOrZero(message = "최소 나이는 음수가 될 수 없습니다.")
    private Integer minAge;
    @PositiveOrZero(message = "최대 나이는 음수가 될 수 없습니다.")
    private Integer maxAge;

    // 최대인원
    @Positive(message = "최대 인원은 0보다 커야합니다.")
    private Integer maxUser;

    // 남자최대인원
    @PositiveOrZero(message = "남자 인원은 음수가 될 수 없습니다.")
    private Integer maxMale;

    // 여자최대인원
    @PositiveOrZero(message = "여자 인원은 음수가 될 수 없습니다.")
    private Integer maxFemale;

    // 모집 유형
    private boolean genderDivisionYn;

    private Category category;

}
