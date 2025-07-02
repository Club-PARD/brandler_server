package com.pard.server.fashion_muse.user.controller.response;

import com.pard.server.fashion_muse.brand.domain.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserScrapBrandResponse {

    private Long brandId;
    private String brandName;
    private String brandLogoUrl;
    private Integer brandGenre;

    public static UserScrapBrandResponse of(Brand brand) {
        return UserScrapBrandResponse.builder()
                .brandId(brand.getId())
                .brandName(brand.getName())
                .brandLogoUrl(brand.getBrandLogoUrl())
                .brandGenre(brand.getBrandGenre())
                .build();
    }

}
