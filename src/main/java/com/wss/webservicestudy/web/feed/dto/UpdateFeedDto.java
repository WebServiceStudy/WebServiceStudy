package com.wss.webservicestudy.web.feed.dto;

import com.wss.webservicestudy.web.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UpdateFeedDto {
    @Builder
    public UpdateFeedDto(String title, String content, LocalDateTime date, String addr, String latitude, String longitude, int maxUser, int minAge, int maxAge) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.addr = addr;
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxUser = maxUser;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;

    // 내용
    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;

    // 모집일
    @NotNull(message = "모집일은 필수 입력값입니다.")
    private LocalDateTime date;

    // 모집 장소
    @NotBlank(message = "주소는 필수 입력값입니다.")
    private String addr;

    // 위도
    private String latitude;

    // 경도
    private String longitude;

    // 최대인원
    @PositiveOrZero(message = "최대 인원은 음수가 될 수 없습니다.")
    private int maxUser;

    // 나이제한
    @PositiveOrZero(message = "최소 나이는 음수가 될 수 없습니다.")
    private int minAge;
    @PositiveOrZero(message = "최대 나이는 음수가 될 수 없습니다.")
    private int maxAge;
}
