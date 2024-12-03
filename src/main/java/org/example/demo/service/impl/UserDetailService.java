package org.example.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.demo.service.facade.Cache;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService {

    private final Cache cache;

    public String getUserDetail(int userId) {
        return cache.get(userId);
    }

    public void addUserDetail(int userId, String userDetail) {
        cache.put(userId, userDetail);
    }
}