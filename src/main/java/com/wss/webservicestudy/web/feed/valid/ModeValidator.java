package com.wss.webservicestudy.web.feed.valid;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class ModeValidator implements ConstraintValidator<Mode, Object> {
    private String mode;
    private String[] modeOnItems;
    private String[] modeOffItems;

    @Override
    public void initialize(Mode constraintAnnotation) {
        this.mode = constraintAnnotation.mode();
        this.modeOnItems = constraintAnnotation.modeOnItems();
        this.modeOffItems = constraintAnnotation.modeOffItems();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null){
            return false;
        }

        boolean modeValue = (boolean) new BeanWrapperImpl(value).getPropertyValue(mode);
        Stream<Object> modeOnItemValues = Arrays.stream(modeOnItems).map(str -> new BeanWrapperImpl(value).getPropertyValue(str));
        Stream<Object> modeOffItemValues = Arrays.stream(modeOffItems).map(str -> new BeanWrapperImpl(value).getPropertyValue(str));

        // On 항목 체크
        if(modeValue){
            return !modeOnItemValues.anyMatch(Objects::isNull)
                    && !modeOffItemValues.anyMatch(Objects::nonNull);
        }

        // Off 항목 체크
        return !modeOnItemValues.anyMatch(Objects::nonNull)
                && !modeOffItemValues.anyMatch(Objects::isNull);
    }
}
