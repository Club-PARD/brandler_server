package com.pard.server.fashion_muse.brand.repository;

import com.pard.server.fashion_muse.brand.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Brandrepository extends JpaRepository<Brand, Long> {
}
