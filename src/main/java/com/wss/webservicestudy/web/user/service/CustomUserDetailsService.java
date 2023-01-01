package com.wss.webservicestudy.web.user.service;

import com.wss.webservicestudy.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.wss.webservicestudy.web.user.entity.User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("사용자 정보를 찾을 수 없습니다");
        }

        return createUserDetails(user);
//        return userRepository.findByEmail(username)
//                .map(this::createUserDetails)
//                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(com.wss.webservicestudy.web.user.entity.User user) {
        log.info("===============login service===============");
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());

        log.info(grantedAuthority.getAuthority());
        return new User(
            user.getEmail(),
            user.getPassword(),
            Collections.singleton(grantedAuthority)
        );
    }
}