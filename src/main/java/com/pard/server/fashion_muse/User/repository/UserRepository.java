package com.pard.server.fashion_muse.User.repository;

import com.pard.server.fashion_muse.User.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
