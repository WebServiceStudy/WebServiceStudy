package com.wss.webservicestudy.web.user.repository;

import com.wss.webservicestudy.web.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
