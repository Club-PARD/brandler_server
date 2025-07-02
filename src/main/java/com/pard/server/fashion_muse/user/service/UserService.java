package com.pard.server.fashion_muse.user.service;

import com.pard.server.fashion_muse.brand.domain.Brand;
import com.pard.server.fashion_muse.brand.repository.Brandrepository;
import com.pard.server.fashion_muse.user.controller.response.UserScrapBrandResponse;
import com.pard.server.fashion_muse.user.domain.User;
import com.pard.server.fashion_muse.user.repository.UserRepository;
import com.pard.server.fashion_muse.userscrap.domain.UserScrap;
import com.pard.server.fashion_muse.userscrap.repository.UserScrapRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Brandrepository brandRepository;
    private final UserScrapRepository userScrapRepository;

    @Transactional
    public List<UserScrapBrandResponse> getUserScrapList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));
        List<UserScrap> brandScraps = userScrapRepository.findAllByUser(user);
        return brandScraps.stream()
                .map(UserScrap::getBrand)
                .map(UserScrapBrandResponse::of)
                .toList();
    }

    @Transactional
    public void deleteUserScrap(Long userId, Long brandId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new RuntimeException("브랜드가 존재하지 않습니다."));

        UserScrap userScrap = userScrapRepository.findByUserAndBrand(user, brand)
                .orElseThrow(() -> new RuntimeException("해당 스크랩이 존재하지 않습니다."));

        userScrapRepository.delete(userScrap);

        brand.setScrapCount(
                brand.getScrapCount() == null || brand.getScrapCount() <= 0
                        ? 0
                        : brand.getScrapCount() - 1
        );
    }

}
