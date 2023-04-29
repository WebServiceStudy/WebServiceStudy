package com.wss.webservicestudy.web.common.util;

import com.wss.webservicestudy.web.common.security.domain.PrincipalDetail;
import com.wss.webservicestudy.web.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {

    private SecurityUtil() { }

    // SecurityContext 에 유저 정보가 저장되는 시점
    // Request 가 들어올 때 JwtFilter 의 doFilter 에서 저장
    public static String getCurrentMember() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw  new RuntimeException("사용자 인증 정보가 없습니다.");
        }
        return authentication.getName();
    }
    public static User getLoginUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Object result = auth.getPrincipal();
        if(result instanceof String) {
            //TODO: Null 예외처리
            return null;
        }
        PrincipalDetail userDetails = (PrincipalDetail) result; //인증받기 위한 정보 //Principal(ID) Credential(pw)
        return userDetails.getUser();
    }

    public static User getLoginUserWithWritable(){
        return getLoginUser().checkIsWritable();
    }
}