package com.wss.webservicestudy.web.common.security.config;

import com.wss.webservicestudy.web.common.security.jwt.JwtAccessDeniedHandler;
import com.wss.webservicestudy.web.common.security.jwt.JwtAuthenticationEntryPoint;
import com.wss.webservicestudy.web.common.security.jwt.JwtTokenProvider;
import com.wss.webservicestudy.web.common.security.oauth.CustomOauthService;
import com.wss.webservicestudy.web.common.security.oauth.OAuth2AuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOauthService customOauthService;
    private final JwtTokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    public SecurityConfig(CustomOauthService customOauthService, JwtTokenProvider tokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler, OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler) {
        this.customOauthService = customOauthService;
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable()
                .formLogin().disable()
                .authorizeRequests()
                    .antMatchers("/api/home/**").permitAll()
                    .antMatchers("/api/auth/**").permitAll()
                    .antMatchers("/api/user/**").hasRole("USER")
                    .antMatchers("/api/feed/**").hasRole("USER")
//                .antMatchers("/api/feed/**").permitAll()
                .anyRequest().authenticated()
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .cors()
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                    .apply(new JwtSecurityConfig(tokenProvider))
                .and()
                    .oauth2Login()
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .userInfoEndpoint()
                    .userService(customOauthService);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}