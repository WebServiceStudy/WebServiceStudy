package com.wss.webservicestudy.web.feed.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) // 클래스, 인터페이스, Enum에 부착할 수 있음.
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 라이프 사이클
@Constraint(validatedBy = ModeValidator.class) // 검증 클래스 설정
public @interface Mode {
    String message() default "항목 확인이 필요합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String mode();
    String[] modeOnItems();
    String[] modeOffItems();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        Mode[] value();
    }
}
