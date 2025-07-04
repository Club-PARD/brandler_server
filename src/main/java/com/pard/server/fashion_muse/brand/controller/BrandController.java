package com.pard.server.fashion_muse.brand.controller;

import com.pard.server.fashion_muse.brand.controller.request.BrandCreateRequest;
import com.pard.server.fashion_muse.brand.controller.responseDto.BrandUpperResponse;
import com.pard.server.fashion_muse.brand.domain.Brand;
import com.pard.server.fashion_muse.user.controller.response.UserScrapResponse;
import com.pard.server.fashion_muse.brand.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        List<BrandUpperResponse> result = brandService.getTop10ScrappedBrands();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody @Valid BrandCreateRequest request) {
        Brand savedBrand = brandService.createBrand(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBrand);
    }

}
