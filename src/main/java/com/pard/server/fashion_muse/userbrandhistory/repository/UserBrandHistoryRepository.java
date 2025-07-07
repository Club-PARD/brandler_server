package com.pard.server.fashion_muse.userbrandhistory.repository;

import com.pard.server.fashion_muse.brand.domain.Brand;
import com.pard.server.fashion_muse.user.domain.User;
import com.pard.server.fashion_muse.userbrandhistory.domain.UserBrandHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBrandHistoryRepository extends JpaRepository<UserBrandHistory, Long> {

    Optional<UserBrandHistory> findByUserAndBrand(User user, Brand brand);

    List<UserBrandHistory> findByUserOrderByViewedAtDesc(User user);
}
