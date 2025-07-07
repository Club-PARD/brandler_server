package com.pard.server.fashion_muse.brand.service;

import com.pard.server.fashion_muse.brand.controller.request.BrandCreateRequest;
import com.pard.server.fashion_muse.brand.controller.responseDto.BrandLowerResponse;
import com.pard.server.fashion_muse.brand.controller.responseDto.BrandResponse;
import com.pard.server.fashion_muse.brand.controller.responseDto.BrandUpperResponse;
import com.pard.server.fashion_muse.user.controller.response.UserScrapResponse;
import com.pard.server.fashion_muse.brand.domain.Brand;
import com.pard.server.fashion_muse.user.domain.User;
import com.pard.server.fashion_muse.user.repository.UserRepository;
import com.pard.server.fashion_muse.userscrap.domain.UserScrap;
import com.pard.server.fashion_muse.userscrap.repository.UserScrapRepository;
import com.pard.server.fashion_muse.brand.repository.Brandrepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final Brandrepository brandRepository;
    private final UserRepository userRepository;
    private final UserScrapRepository userScrapRepository;

    @Transactional
    public List<BrandUpperResponse> getTop10ScrappedBrands() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Object[]> results = userScrapRepository.findTopBrandsByScrapCount(pageable);

        return results.stream()
                .map(row -> BrandUpperResponse.builder()
                        .brandId((Long) row[0])
                        .brandName((String) row[1])
                        .brandLogoUrl((String) row[2])
                        .scrapCount((Long) row[3])
                        .build())
                .toList();
    }

    @Transactional
    public List<BrandLowerResponse> getLowScrapBrands(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        List<Object[]> results = userScrapRepository.findBrandsByScrapAscAndProductDesc(pageable);

        return results.stream()
                .map(row -> BrandLowerResponse.builder()
                        .brandId((Long) row[0])
                        .brandName((String) row[1])
                        .brandLogoUrl((String) row[2])
                        .scrapCount((Long) row[3])
                        .productCount((Long) row[4])
                        .build())
                .toList();
    }

    @Transactional
    public BrandResponse getBrand(Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new RuntimeException("브랜드가 존재하지 않습니다."));

        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .brandLogoUrl(brand.getBrandLogoUrl())
                .brandBannerUrl(brand.getBrandBannerUrl())
                .brandGenre(brand.getBrandGenre().name())
                .brandHomepageUrl(brand.getBrandHomepageUrl())
                .description(brand.getDescription())
                .build();
    }

    @Transactional
    public Brand createBrand(BrandCreateRequest request) {
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setBrandLogoUrl(request.getBrandLogoUrl());
        brand.setBrandBannerUrl(request.getBrandBannerUrl());
        brand.setBrandGenre(request.getBrandGenre());
        brand.setBrandHomepageUrl(request.getBrandHomepageUrl());

        return brandRepository.save(brand);
    }

}
