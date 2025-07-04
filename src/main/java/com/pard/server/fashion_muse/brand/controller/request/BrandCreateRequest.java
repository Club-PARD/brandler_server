package com.pard.server.fashion_muse.brand.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandCreateRequest {

    private String name;
    private String brandLogoUrl;
    private String brandBannerUrl;
    private Integer brandGenre;
    private String brandHomepageUrl;

}
