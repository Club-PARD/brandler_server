package com.pard.server.fashion_muse.brand.controller.responseDto;

import com.pard.server.fashion_muse.brand.domain.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandUpperResponse {

    private Long brandId;
    private String brandName;
    private String brandLogoUrl;
    private Integer scrapCount;

    public static BrandUpperResponse of(Brand brand) {
        return BrandUpperResponse.builder()
                .brandId(brand.getId())
                .brandName(brand.getName())
                .brandLogoUrl(brand.getBrandLogoUrl())
                .scrapCount(brand.getScrapCount())
                .build();
    }
}
