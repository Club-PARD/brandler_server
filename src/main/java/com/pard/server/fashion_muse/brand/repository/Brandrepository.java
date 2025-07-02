package com.pard.server.fashion_muse.brand.repository;

import com.pard.server.fashion_muse.brand.domain.Brand;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Brandrepository extends JpaRepository<Brand, Long> {
    List<Brand> findTop10ByOrderByScrapCountDesc();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select b from Brand b where b.id = :id")
    Brand findByIdWithLock(@Param("id") Long id);

}
