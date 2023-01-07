package com.wss.webservicestudy.web.common.security.jwt;

import com.wss.webservicestudy.web.common.security.jwt.dto.TokenInfo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
@PropertySource("classpath:secretkey.properties")
public class JwtTokenProvider {

    private static final String BEARER_TYPE = "Bearer";
    private static final String AUTHORITIES_KEY = "auth";
    // 토큰 유효시간 30분
    static final long accessTokenValidTime = 1000L * 60 * 30;
    // 토큰 유효시간 30일
    static final long refreshTokenValidTime = 1000L * 60 * 60 * 24 * 30;
    private final Key key;


    // 객체 초기화, secretKey를 Base64로 인코딩한다.

    public JwtTokenProvider(@Value("${jwt.secret.key}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 유저 정보를 넘겨받아서 Access Token 과 Refresh Token 을 생성합니다.
     * 넘겨받은 유저 정보의 authentication.getName() 메소드가 username 을 가져옵니다.
     * 저는 username 으로 Member ID 를 저장했기 때문에 해당 값이 설정될 겁니다.
     * Access Token 에는 유저와 권한 정보를 담고 Refresh Token 에는 아무 정보도 담지 않습니다.
     * @param authentication
     * @return TokenInfo
     */
    public TokenInfo createToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + accessTokenValidTime);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + refreshTokenValidTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return TokenInfo.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .build();
    }

    /**
     * JWT 토큰을 복호화하여 토큰에 들어 있는 정보를 꺼냅니다.
     * Access Token 에만 유저 정보를 담기 때문에 명시적으로 accessToken 을 파라미터로 받음
     * Refresh Token 에는 아무런 정보 없이 만료일자만
     * UserDetails 객체를 생성해서 UsernamePasswordAuthenticationToken 형태로 리턴하는데 SecurityContext 를 사용하기 위한 절차라고 생각하면 됩니다..
     * 사실 좀 불필요한 절차라고 생각되지만 SecurityContext가 Authentication 객체를 저장하기 때문에 어쩔수 없습니다.
     * parseClaims 메소드는 만료된 토큰이어도 정보를 꺼내기 위해서 따로 분리했습니다.
     * @param accessToken
     * @return
     */
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        log.info(claims.getSubject());
        log.info(claims.get(AUTHORITIES_KEY).toString());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw new RuntimeException("인증 에러");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw new RuntimeException("인증 에러");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
            throw new RuntimeException("인증 에러");
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}