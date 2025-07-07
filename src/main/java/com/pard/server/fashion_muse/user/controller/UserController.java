package com.pard.server.fashion_muse.user.controller;

import com.pard.server.fashion_muse.brand.controller.responseDto.BrandResponse;
import com.pard.server.fashion_muse.user.controller.request.UserCreateRequest;
import com.pard.server.fashion_muse.user.controller.response.UserResponse;
import com.pard.server.fashion_muse.user.controller.response.UserScrapBrandResponse;
import com.pard.server.fashion_muse.user.controller.response.UserScrapResponse;
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
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/{brandId}/scraps")
    public ResponseEntity<UserScrapResponse> userScrap(@PathVariable Long brandId,
                                                       @RequestParam String email) {
        User user = userService.findByEmail(email);
        UserScrapResponse response = userService.userScrap(user.getId(), brandId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/scraps")
    public ResponseEntity<List<UserScrapBrandResponse>> getUserBrandScraps(@RequestParam String email) {
        User user = userService.findByEmail(email);
        List<UserScrapBrandResponse> scraps = userService.getUserScrapList(user.getId());
        return ResponseEntity.ok(scraps);
    }

    @DeleteMapping("/scraps")
    public ResponseEntity<Void> deleteUserBrandScrap(@RequestParam String email,
                                                     @RequestBody List<Long> brandIds) {
        User user = userService.findByEmail(email);
        userService.deleteUserScrap(user.getId(), brandIds);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<BrandResponse>> getRecentBrands(@RequestParam String email) {
        User user = userService.findByEmail(email);
        List<BrandResponse> result = userService.getRecentBrands(user.getId());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/auth/check-email")
    public ResponseEntity<Void> checkEmail(@RequestParam String email) {
        boolean exists = userService.existsByEmail(email);
        if (exists) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        User savedUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.of(savedUser));
    }
}
