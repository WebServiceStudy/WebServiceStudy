package com.wss.webservicestudy.web.common.security.jwt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenRequestDto {
    private String accessToken;
    private String refreshToken;
}