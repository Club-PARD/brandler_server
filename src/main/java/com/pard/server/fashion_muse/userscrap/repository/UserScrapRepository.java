package com.pard.server.fashion_muse.userscrap.repository;

import com.pard.server.fashion_muse.userscrap.domain.UserScrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScrapRepository extends JpaRepository<UserScrap, Long> {
}
