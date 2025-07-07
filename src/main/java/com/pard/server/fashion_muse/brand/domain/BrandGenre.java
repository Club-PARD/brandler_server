package com.pard.server.fashion_muse.brand.domain;

import java.util.Arrays;

public enum BrandGenre {
    아메카지,
    스트릿,
    빈티지,
    히피,
    포멀,
    페미닌,
    테크,
    펑크,
    기타;

    public static BrandGenre from(String genreName) {
        return Arrays.stream(BrandGenre.values())
                .filter(g -> g.name().equals(genreName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장르입니다: " + genreName));
    }

}
