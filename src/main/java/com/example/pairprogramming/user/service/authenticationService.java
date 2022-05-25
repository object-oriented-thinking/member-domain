package com.example.pairprogramming.user.service;

import com.example.pairprogramming.user.domain.UserEntity;

public interface authenticationService {
    // 1. token 전송
    //void sendTokenToEmail(UserEntity userEntity);
    // 2. token 체크
    //boolean verifyToken(UserEntity userEntity, String token);
    /*
    사용자서비스

    userEnttiy.setEmail("test@test.com");
    ...
    AuthenticationDetails token = authService.sendTokenToEmail(userEntity.getEmail);
    회원가입 성공 !!

    사용자 이메일에서 확인하는 링크

    check(String token, ...) {
    userEnitiy == email?
    boolean result = authService.checkToken(token);
    if result == true {
        회원가입 성공!
    } else {
     실패!
     }

    }
     */
}
