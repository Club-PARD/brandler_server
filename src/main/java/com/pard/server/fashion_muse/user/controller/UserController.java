package com.pard.server.fashion_muse.user.controller;

import com.pard.server.fashion_muse.user.controller.response.UserScrapBrandResponse;
import com.pard.server.fashion_muse.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}/scraps")
    public ResponseEntity<List<UserScrapBrandResponse>> getUserBrandScraps(@PathVariable Long userId) {
        //로그인 안한 상태여서 userId임시로 사용
        List<UserScrapBrandResponse> scraps = userService.getUserScrapList(userId);
        return ResponseEntity.ok(scraps);
    }

    @DeleteMapping("/{userId}/scraps/{brandId}")
    public ResponseEntity<Void> deleteUserBrandScrap(@PathVariable Long userId, @PathVariable Long brandId) {
        userService.deleteUserScrap(userId, brandId);
        return ResponseEntity.ok(null);
    }
}
