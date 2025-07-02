package com.pard.server.fashion_muse.userscrap.repository;

import com.pard.server.fashion_muse.brand.domain.Brand;
import com.pard.server.fashion_muse.user.domain.User;
import com.pard.server.fashion_muse.userscrap.domain.UserScrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserScrapRepository extends JpaRepository<UserScrap, Long> {
    Optional<UserScrap> findByUserAndBrand(User user, Brand brand);
    List<UserScrap> findAllByUser(User user);
}
