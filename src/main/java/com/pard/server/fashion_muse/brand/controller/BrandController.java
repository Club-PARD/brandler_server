package com.pard.server.fashion_muse.brand.controller;

import com.pard.server.fashion_muse.brand.controller.request.BrandCreateRequest;
import com.pard.server.fashion_muse.brand.controller.responseDto.BrandLowerResponse;
import com.pard.server.fashion_muse.brand.controller.responseDto.BrandResponse;
import com.pard.server.fashion_muse.brand.controller.responseDto.BrandUpperResponse;
import com.pard.server.fashion_muse.brand.domain.Brand;
import com.pard.server.fashion_muse.user.controller.response.UserScrapResponse;
import com.pard.server.fashion_muse.brand.service.BrandService;
import com.pard.server.fashion_muse.user.domain.User;
import com.pard.server.fashion_muse.user.service.UserService;
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
    private final UserService userService;

    @GetMapping("/top")
    public ResponseEntity<List<BrandUpperResponse>> getTopBrands() {
        List<BrandUpperResponse> result = brandService.getTop10ScrappedBrands();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/low")
    public ResponseEntity<List<BrandLowerResponse>> getLowScrapBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<BrandLowerResponse> result = brandService.getLowScrapBrands(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<BrandResponse> getBrand(@PathVariable Long brandId,
                                                  @RequestParam String email) {
        User user = userService.findByEmail(email);
        brandService.saveBrandHistory(user.getId(), brandId);
        BrandResponse response = brandService.getBrand(brandId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody @Valid BrandCreateRequest request) {
        Brand savedBrand = brandService.createBrand(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBrand);
    }

}
