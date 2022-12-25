package com.wss.webservicestudy.web.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    /* front */
    public static String FRONT_URL;
    /* accessToken TTL */
    public static final Long ACCESS_TOKEN_TTL = 30L;
    @Value("${config.cors.front-url}")
    public void setFrontUrl(String url){
        Constants.FRONT_URL = url;
    }
}
