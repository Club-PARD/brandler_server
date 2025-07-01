package com.pard.server.fashion_muse.user.repository;

import com.pard.server.fashion_muse.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
