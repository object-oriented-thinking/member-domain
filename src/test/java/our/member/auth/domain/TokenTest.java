package our.member.auth.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import our.member.auth.domain.Token;
import our.member.auth.error.InvalidTokenException;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static our.member.auth.fixture.TokenFixture.*;

class TokenTest {

    @Test
    @DisplayName("인증 토큰은 인증 토큰을 가진다.")
    void test1() {
        //given
        Token token = new Token(TOKEN_VALUE);
        //when & then
        assertThat(token.getToken()).isEqualTo(TOKEN_VALUE);
    }

    @Test
    @DisplayName("인증 토큰에서 이메일과 유효시각 정보 분리 단위가 `---` 아니면, 예외가 발생한다.")
    void test4() {
        assertThatThrownBy(
            () -> new Token(TOKEN_EMAIL + TOKEN_EXPIRED_TIME)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("인증 정보가 존재하지 않으면 IllegalArgumentException 예외가 발생한다.")
    void test5_1() {
        assertThatThrownBy(
            () -> new Token("")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유효하지 않은 이메일이면 IllegalArgumentException 예외가 발생한다.")
    void test5_2() {
        assertThatThrownBy(
            () -> new Token(NOT_INVALID_TOKEN_EMAIL + "---" + TOKEN_EXPIRED_TIME)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유효하지 않은 생성일자이면 IllegalArgumentException 예외가 발생한다.")
    void test5_3() {
        assertThatThrownBy(
            () -> new Token(TOKEN_EMAIL + "---" + NOT_INVALID_TOKEN_EXPIRED_TIME)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("인증 토큰을 통해해당 지원자의 이메일 정보와 유효 시각 정보를 가진다.")
    void test2() {
        //given
        Token token = new Token(TOKEN_VALUE);
        //when & then
        Assertions.assertAll(
            () -> assertThat(token.getEmail()).isEqualTo(TOKEN_EMAIL),
            () -> assertThat(token.getExpiredTime()).isEqualTo(parse(TOKEN_EXPIRED_TIME, LOCAL_DATE_TIME_FORMATTER))
        );
    }

    @Test
    @DisplayName("지원자의 이메일 정보를 가져와 인증 토큰을 생성한다.")
    void test3() {
        Assertions.assertDoesNotThrow(
            () -> Token.createToken(TOKEN_EMAIL)
        );
    }

    @Test
    @DisplayName("지원자의 이메일 정보와 현재 시각에서 30분이 지난 시각을 가지고 인증 토큰을 생성한다.")
    void test6() {
        Token token = Token.createToken(TOKEN_EMAIL);
        LocalDateTime after29min = now().plusMinutes(29);
        assertThat(token.getExpiredTime()).isAfter(after29min);
    }


    @Test
    @DisplayName("유효 시각이 지났다면 `InvalidTokenException` 예외가 발생한다.")
    void test10() {
        assertThatThrownBy(
            () -> new Token(OUT_OF_DATE_TOKEN_VALUE)
        ).isInstanceOf(InvalidTokenException.class);
    }
}
