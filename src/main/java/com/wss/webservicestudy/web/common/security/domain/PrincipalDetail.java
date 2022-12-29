package com.wss.webservicestudy.web.common.security.domain;

import com.wss.webservicestudy.web.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class PrincipalDetail implements OAuth2User {

    private User user;

    private Map<String, Object> attributes;

    public PrincipalDetail() {}

    public PrincipalDetail(User user, Map<String, Object> attributes) {
        this.user=user;
        this.attributes=attributes;
    }

    public PrincipalDetail(User user) {
        this.user = user;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();

        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole().name();
            }
        });
        return collect;
    }

    @Override
    public String getName() {
        return this.user.getName();
    }
}
