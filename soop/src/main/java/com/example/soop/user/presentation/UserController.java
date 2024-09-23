package com.example.soop.user.presentation;

import com.example.soop.auth.domain.Accessor;
import com.example.soop.auth.domain.Auth;
import com.example.soop.user.dto.request.SavePhoneRequest;
import com.example.soop.user.dto.response.UserResponse;
import com.example.soop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getLoginUserInfo(@Auth final Accessor user) {
        return ResponseEntity.ok(userService.getUserInfo(user.getMemberId()));
    }

    @PostMapping("/phone")
    public ResponseEntity<?> saveUserPhone(@Auth final Accessor accessor, @RequestBody SavePhoneRequest req){
        userService.saveUserPhone(accessor.getMemberId(), req.phone());
        return ResponseEntity.ok().build();
    }
}
