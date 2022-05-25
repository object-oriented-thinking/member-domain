package com.example.pairprogramming.user.domain;

import com.example.pairprogramming.user.error.ExpiredTokenException;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.*;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * Token은 email과 현재 시간을 기준으로 생성된다.
 *
 * token -> Entity
 *       LocalDate email + date => Token
 *      decoding = > date < current date => error
 */
public class Token {
    private TokenValue tokenValue;
    private String email;
    private LocalDateTime createdAt;

    public Token(String token, String email, LocalDateTime createdAt) {
        if (createdAt.plusMinutes(30).isBefore(now())) {
            throw new ExpiredTokenException();
        }
        this.tokenValue = new TokenValue(token, email);
    }

//    public String dateFormat(LocalDateTime date) {
//        return ofPattern("yyyy-MM-dd HH:mm:ss").format(date);
//    }

}
