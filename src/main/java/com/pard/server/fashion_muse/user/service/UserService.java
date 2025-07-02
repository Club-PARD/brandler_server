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
    public void deleteUserScrap(Long userId, List<Long> brandIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));

        List<Brand> brands = brandRepository.findAllById(brandIds);

        for (Brand brand : brands) {
            UserScrap userScrap = userScrapRepository.findByUserAndBrand(user, brand)
                    .orElseThrow(() -> new RuntimeException("스크랩 완료된 유저의 브랜드가 존재하지 않습니다."));

            if (userScrap != null) {
                userScrapRepository.delete(userScrap);
                brand.setScrapCount(
                        brand.getScrapCount() == null || brand.getScrapCount() <= 0
                                ? 0
                                : brand.getScrapCount() - 1
                );
            }
        }
    }

}
