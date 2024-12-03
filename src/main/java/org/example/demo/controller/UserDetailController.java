package org.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.example.demo.service.impl.UserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-detail")
@RequiredArgsConstructor
public class UserDetailController {

    private final UserDetailService userDetailService;

    @GetMapping("/{userId}")
    public ResponseEntity<String> getUserDetail(@PathVariable int userId) {
        return ResponseEntity.ok(userDetailService.getUserDetail(userId));
    }

    @PostMapping("/{userId}/{userDetail}")
    public ResponseEntity<Void> addUserDetail(@PathVariable int userId, @PathVariable String userDetail) {
        userDetailService.addUserDetail(userId, userDetail);
        return ResponseEntity.noContent().build();
    }
}