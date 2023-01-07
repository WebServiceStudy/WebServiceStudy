package com.wss.webservicestudy.web.user.dto;

import com.wss.webservicestudy.web.user.entity.User;
import com.wss.webservicestudy.web.user.type.Role;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginReqDto {
    private String email;
    private String password;

    private String name;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .name(name)
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(this.email, this.password);
    }
}
