package com.example.pairprogramming.user.domain;

import com.example.pairprogramming.user.error.NotTokenFormatException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TokenVo 역할
 */

public class TokenValue {
    private final String tokenValue;

    public TokenValue(String tokenValue, String email) {
        // token = "email,LocalDateTime.now()";
        String emailFromToken = tokenValue.split(",")[0];
        String dateFromToken = tokenValue.split(",")[1];

        if (!emailFromToken.equals(email)){
            throw new NotTokenFormatException();
        }

        String regx = "/[0-9]{4}-[0-9]{2}-[0-9]{2}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}/";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(dateFromToken);

        //TODO 토큰 생성 시 date를 "yyyy-MM-dd HH:mm:ss" 형태로 생성하여 유효성 검사를 시도하였습니다.
        // Token에서 넣어주는 date가 잘못되었는지 AuthenticationDetails에서 date 잘못되었는지 모르겟지만 오류가 발생하여 조언구합니다.
//        if (!matcher.matches()) {
//            throw new NotTokenFormatException();
//        }

        this.tokenValue = tokenValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenValue that = (TokenValue) o;
        return Objects.equals(tokenValue, that.tokenValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenValue);
    }
}
