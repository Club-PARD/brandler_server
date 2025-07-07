package com.pard.server.fashion_muse.brand.controller.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {

    private Long id;
    private String name;
    private String brandLogoUrl;
    private String brandBannerUrl;
    private String brandGenre;
    private String brandHomepageUrl;
    private String description;
}
