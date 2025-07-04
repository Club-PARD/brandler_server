package com.pard.server.fashion_muse.userscrap.repository;

import com.pard.server.fashion_muse.brand.domain.Brand;
import com.pard.server.fashion_muse.user.domain.User;
import com.pard.server.fashion_muse.userscrap.domain.UserScrap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserScrapRepository extends JpaRepository<UserScrap, Long> {
    Optional<UserScrap> findByUserAndBrand(User user, Brand brand);
    List<UserScrap> findAllByUser(User user);

    @Query("SELECT COUNT(us) FROM UserScrap us WHERE us.brand.id = :brandId")
    Long countByBrandId(@Param("brandId") Long brandId);

    @Query("""
    select 
        us.brand.id,
        us.brand.name,
        us.brand.brandLogoUrl,
        count(us.id)
    from UserScrap us
    group by 
        us.brand.id,
        us.brand.name,
        us.brand.brandLogoUrl
    order by count(us.id) desc
""")
    List<Object[]> findTopBrandsByScrapCount(Pageable pageable);

}
