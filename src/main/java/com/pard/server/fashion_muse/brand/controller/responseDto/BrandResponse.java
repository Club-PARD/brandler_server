package com.pard.server.fashion_muse.brand.controller.responseDto;

import com.pard.server.fashion_muse.product.controller.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<ProductResponse> products;
}
