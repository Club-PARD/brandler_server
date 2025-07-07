package com.pard.server.fashion_muse.product.controller.response;

import com.pard.server.fashion_muse.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponse {

    private Long id;
    private String productName;
    private String productImageUrl;
    private String productCategory;
    private String price;

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productImageUrl(product.getProductImageUrl())
                .productCategory(product.getProductCategory())
                .price(product.getProductPrice())
                .build();
    }
}
