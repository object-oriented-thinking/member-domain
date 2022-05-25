package com.example.pairprogramming.user.service;

import com.example.pairprogramming.user.domain.AuthenticationDetails;
import com.example.pairprogramming.user.domain.UserEntity;
import com.example.pairprogramming.user.repository.TokenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserAuthenticationService implements authenticationService{
    private final TokenRepository tokenRepository;
    private static Map<String, String> mailSender = new HashMap<>();

    public UserAuthenticationService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }
/*
    @Override
    public void sendTokenToEmail(UserEntity userEntity) {
        // token 생성
        String token = userEntity.getEmail() + userEntity.getUsername();
        AuthenticationDetails authenticationDetailsEntity = new AuthenticationDetails();
        authenticationDetailsEntity.setToken(token);
        authenticationDetailsEntity.setUserId(userEntity.getUserId());
        tokenRepository.save(authenticationDetailsEntity);

        mailSender.put("email", userEntity.getEmail());
        mailSender.put("title", "인증 이메일");
        mailSender.put("message", "인증 토큰: "+token);

        for (String key : mailSender.keySet()) {
            System.out.println(key + ": " + mailSender.get(key));
        }


    }

    @Override
    public boolean verifyToken(UserEntity userEntity, String token) {
        Optional<AuthenticationDetails> tokenOptional = tokenRepository.findByToken(token);
        if (tokenOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        // 존재함
        AuthenticationDetails existAuthenticationDetails = tokenOptional.get();
        if (existAuthenticationDetails.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("인증 유효시간이 만료된 토큰입니다.");
        }

        if (!existAuthenticationDetails.getTokenId().equals(userEntity.getUserId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        return true;
    }
*/


}
