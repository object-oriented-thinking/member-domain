package our.member.auth.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import our.member.auth.domain.AuthenticationDetails;
import our.member.auth.domain.Token;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static our.member.auth.fixture.TokenFixture.TOKEN_VALUE;

class AuthenticationDetailsTest {

    @Test
    @DisplayName("인증은 인증코드 식별자, 인증하려는 지원자의 식별자, 인증 토큰의 정보를 가진다.")
    void test1() {
        //given
        UUID authenticationId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Token token = new Token(TOKEN_VALUE);
        //when
        AuthenticationDetails details = new AuthenticationDetails(authenticationId, userId, token);
        //then
        assertAll(
            () -> assertThat(details.getAuthenticationId()).isEqualTo(authenticationId),
            () -> assertThat(details.getMemberId()).isEqualTo(userId),
            () -> assertThat(details.getToken()).isEqualTo(token)
        );
    }
}
