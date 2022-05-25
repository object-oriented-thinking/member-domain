package com.example.pairprogramming;

import com.example.pairprogramming.user.domain.AuthenticationDetails;
import com.example.pairprogramming.user.domain.Token;
import com.example.pairprogramming.user.domain.TokenState;
import com.example.pairprogramming.user.domain.UserEntity;
import com.example.pairprogramming.user.error.NotTokenFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static java.time.LocalDateTime.*;
import static java.time.format.DateTimeFormatter.*;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationDetailsTest {

    @Test
    @DisplayName("토큰과 유저와 토큰상태가 들어간다.")
    void testAuthenticationDetails() {
        UserEntity user = new UserEntity(1L, "Kim", "kim@gmail.com", "1234", "ROLE_USER");
        String token  = user.getEmail() + "," + now().format(ofPattern("yyyy-MM-dd HH:mm:ss"));
        assertDoesNotThrow(() -> new AuthenticationDetails(UUID.randomUUID(), token, user, TokenState.VALID));
    }

    @Test
    @DisplayName("Token에 이메일이 아닌 다른 값이 들어가면 NotTokenFormatException이 발생한다.")
    void testToken() {
        UserEntity user = new UserEntity(1L, "Kim", "kim@gmail.com", "1234", "ROLE_USER");
        String token  = user.getUsername() + "," + now().format(ofPattern("yyyy-MM-dd HH:mm:ss"));
        assertThrows(NotTokenFormatException.class, ()-> {
            new Token(token, user.getEmail(), now());
        });
    }

}
