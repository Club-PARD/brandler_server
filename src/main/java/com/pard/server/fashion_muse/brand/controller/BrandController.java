package com.pard.server.fashion_muse.brand.controller;

import com.pard.server.fashion_muse.brand.controller.responseDto.BrandUpperResponse;
import com.pard.server.fashion_muse.user.controller.response.UserScrapResponse;
import com.pard.server.fashion_muse.brand.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/brands/top")
    public ResponseEntity<List<BrandUpperResponse>> getTopBrands() {
        List<BrandUpperResponse> topBrands = brandService.getTop10ScrappedBrands();
        return ResponseEntity.ok(topBrands);
    }

}
