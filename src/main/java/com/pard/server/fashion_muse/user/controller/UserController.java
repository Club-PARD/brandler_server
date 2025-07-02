package com.pard.server.fashion_muse.user.controller;

import com.pard.server.fashion_muse.user.controller.response.UserScrapBrandResponse;
import com.pard.server.fashion_muse.user.controller.response.UserScrapResponse;
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

    @PostMapping("/{brandId}/scraps")
    public ResponseEntity<UserScrapResponse> userScrap(@PathVariable Long brandId) {
        //현재는 임시, 로그인을 안했기 때문
        Long userId = 1L;
        UserScrapResponse response = userService.userScrap(userId, brandId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/scraps")
    public ResponseEntity<List<UserScrapBrandResponse>> getUserBrandScraps(@PathVariable Long userId) {
        //로그인 안한 상태여서 userId임시로 사용
        List<UserScrapBrandResponse> scraps = userService.getUserScrapList(userId);
        return ResponseEntity.ok(scraps);
    }

    @DeleteMapping("/{userId}/scraps/{brandId}")
    public ResponseEntity<Void> deleteUserBrandScrap(@PathVariable Long userId,
                                                     @RequestBody List<Long> brandIds) {
        userService.deleteUserScrap(userId, brandIds);
        return ResponseEntity.ok(null);
    }
}
