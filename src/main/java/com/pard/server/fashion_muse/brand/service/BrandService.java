package com.pard.server.fashion_muse.brand.service;

import com.pard.server.fashion_muse.brand.controller.responseDto.UserScrapResponse;
import com.pard.server.fashion_muse.brand.domain.Brand;
import com.pard.server.fashion_muse.user.domain.User;
import com.pard.server.fashion_muse.user.repository.UserRepository;
import com.pard.server.fashion_muse.userscrap.domain.UserScrap;
import com.pard.server.fashion_muse.userscrap.repository.UserScrapRepository;
import com.pard.server.fashion_muse.brand.repository.Brandrepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final Brandrepository brandrepository;
    private final UserRepository userrepository;
    private final UserScrapRepository userscraprepository;

    @Transactional
    public UserScrapResponse userScrap(Long userId, Long brandId) {
        User user = userrepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("유저가 존재하지 않습니다."));
        Brand brand = brandrepository.findById(brandId)
                .orElseThrow(()-> new RuntimeException("브랜드가 존재하지 않습니다."));

        Optional<UserScrap> findUserScrap = userscraprepository.findByUserAndBrand(user, brand);
        boolean isScrapped;

        if (findUserScrap.isPresent()) {
            userscraprepository.delete(findUserScrap.get());
            brand.setScrapCount(brand.getScrapCount() - 1);
            isScrapped = false;
        }else{
            UserScrap scrap = new UserScrap(null, user, brand);
            userscraprepository.save(scrap);
            brand.setScrapCount(
                    brand.getScrapCount() == null ? 1 : brand.getScrapCount() + 1
            );
            isScrapped = true;
        }
        return UserScrapResponse.of(brandId, isScrapped);
    }
}
