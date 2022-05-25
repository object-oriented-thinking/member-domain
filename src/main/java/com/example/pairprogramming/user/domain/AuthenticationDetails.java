package com.example.pairprogramming.user.domain;

import com.example.pairprogramming.user.error.InvalidTokenException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static java.time.LocalDateTime.*;
import static java.time.format.DateTimeFormatter.*;

/**
 * 인증 토큰은 해당 지원자의 이메일 정보와 유효 시각 정보를 가진다.
 * 유효 시간은 등록하는 시각에서 30분이 지난 시각이다.
 */
public class AuthenticationDetails {
    private UUID id;
    private Token token;
    private UserEntity userEntity;
    private TokenState tokenState;

    public AuthenticationDetails(UUID id, String token, UserEntity userEntity, TokenState tokenState) {
        if (!tokenState.equals(TokenState.VALID)) {
            throw new InvalidTokenException();
        }
        this.token = new Token(token, userEntity.getEmail(), now());
        this.userEntity = userEntity;
        this.tokenState = tokenState ;
    }

}
