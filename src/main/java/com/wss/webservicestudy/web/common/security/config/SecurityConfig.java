package com.wss.webservicestudy.web.common.security.config;

import com.wss.webservicestudy.web.common.security.core.userdetails.CustomOAuth2UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity //웹보안 활성화를위한 annotation
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests() // 요청에 의한 보안검사 시작
                .antMatchers("/user").permitAll()
                .anyRequest().authenticated() //어떤 요청에도 보안검사를 한다.
                .and()
                .formLogin();//보안 검증은 formLogin방식으로 하겠다.

        http
                .oauth2Login()
                .loginPage("/loginForm")
                .defaultSuccessUrl("/")
                .failureUrl("/loginForm")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

    }
}