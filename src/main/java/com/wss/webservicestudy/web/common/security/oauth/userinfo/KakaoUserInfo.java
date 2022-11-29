package com.wss.webservicestudy.web.common.security.oauth.userinfo;

import java.util.HashMap;
import java.util.Map;

public class KakaoUserInfo implements OAuthUserInfo {
    Map<String, Object> kakao_account;

    public KakaoUserInfo(Map<String, Object> account) {
        Map<String, Object> attribute =  (Map<String, Object>) account.get("kakao_account");
        this.kakao_account = (Map<String, Object>) attribute.get("profile");
    }

}
