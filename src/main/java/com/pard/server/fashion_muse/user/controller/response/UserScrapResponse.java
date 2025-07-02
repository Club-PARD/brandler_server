package com.pard.server.fashion_muse.user.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserScrapResponse {

    private Long brandId;
    private boolean scrapped;

    public static UserScrapResponse of(Long brandId, boolean scrapped) {
        return UserScrapResponse.builder()
                .brandId(brandId)
                .scrapped(scrapped)
                .build();
    }
}
