package com.pard.server.fashion_muse.brand.controller;

import com.pard.server.fashion_muse.brand.controller.responseDto.UserScrapResponse;
import com.pard.server.fashion_muse.brand.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/{brandId}/scrap")
    public ResponseEntity<UserScrapResponse> userScrap(@PathVariable Long brandId) {
        //현재는 임시 로그인을 안했기 때문
        Long userId = 1L;
        UserScrapResponse response = brandService.userScrap(userId, brandId);
        return ResponseEntity.ok(response);
    }
}
