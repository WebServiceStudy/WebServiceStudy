package com.wss.webservicestudy.web.feed.valid;

import com.wss.webservicestudy.web.feed.dto.CreateFeedDto;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class FeedValidationTest {
    @Test
    public void createFeedDtoTest(){
        // Given
        String title = "WebServiceStudy1";
        String content = "미팅 웹 페이지 만들기 스터디";
        LocalDateTime date = LocalDateTime.of(2023,5,8,10,0,0);
        String addr = "강남";
        String latitude = "latitude";
        String longitude = "longitude";
        int minAge = 20;
        int maxAge = 31;
        boolean genderDivisionYn = false;
        Integer maxUser = 1;
        Integer maxMale = null;
        Integer maxFemale = null;

        CreateFeedDto dto = CreateFeedDto.builder()
                .title(title)
                .content(content)
                .date(date)
                .addr(addr)
                .latitude(latitude)
                .longitude(longitude)
                .maxUser(maxUser)
                .minAge(minAge)
                .maxAge(maxAge)
                .genderDivisionYn(genderDivisionYn)
                .maxFemale(maxMale)
                .maxMale(maxFemale)

                .build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // When
        Set<ConstraintViolation<CreateFeedDto>> violations = validator.validate(dto);
        violations
                .forEach(error -> {
                    System.out.println(error.getMessage());
                });

        // 유효성 검사 실패하면 성공
        assertFalse(violations.isEmpty());
    }
}
