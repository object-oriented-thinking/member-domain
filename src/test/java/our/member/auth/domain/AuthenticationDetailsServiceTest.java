package our.member.auth.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import our.member.auth.domain.*;
import our.member.auth.error.NotAllowedException;
import our.member.auth.fake.FakeAuthenticationDetailsRepository;
import our.member.auth.fake.FakeConfirmationApplicantService;
import our.member.auth.fake.FakeNotificationService;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static our.member.auth.fixture.AuthenticationFixture.APPLICANT_MEMBER_ID;
import static our.member.auth.fixture.AuthenticationFixture.NOT_INVALID_EMAIL_MEMBER_ID;
import static our.member.auth.fixture.TokenFixture.TOKEN_VALUE;

class AuthenticationDetailsServiceTest {

    private AuthenticationDetailsRepository authenticationDetailsRepository = new FakeAuthenticationDetailsRepository();
    private ConfirmationApplicantService confirmationApplicantService = new FakeConfirmationApplicantService();
    private NotificationService notificationService = new FakeNotificationService();

    private AuthenticationDetailsService authenticationDetailsService = new AuthenticationDetailsService(confirmationApplicantService, notificationService, authenticationDetailsRepository);

    @Test
    @DisplayName("지원자의 식별자를 가져와 인증을 요청한다.")
    void test1() {
        UUID userId = UUID.randomUUID();
        assertThat(authenticationDetailsService.requestAuthentication(userId)).isTrue();
    }

    @Test
    @DisplayName("이미 보낸 지원자이면 존재하는 인증에서 인증토큰 정보를 갱신한다.")
    void test2() {
        //given
        UUID authenticationId = UUID.randomUUID();
        UUID userId = APPLICANT_MEMBER_ID;
        Token token = new Token(TOKEN_VALUE);
        AuthenticationDetails authenticationDetails = new AuthenticationDetails(authenticationId, userId, token);
        //when
        authenticationDetailsRepository.save(authenticationDetails);
        //then
        assertDoesNotThrow(
            () -> authenticationDetailsService.requestAuthentication(userId)
        );
    }

    @Test
    @DisplayName("지원자가 아닌 회원인 경우 `NotAllowedException` 예외가 발생한다.")
    void test3() {
        //given
        UUID authenticationId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Token token = new Token(TOKEN_VALUE);
        AuthenticationDetails authenticationDetails = new AuthenticationDetails(authenticationId, userId, token);

        //when
        authenticationDetailsRepository.save(authenticationDetails);

        //then
        assertThatThrownBy(
            () -> authenticationDetailsService.requestAuthentication(userId)
        ).isInstanceOf(NotAllowedException.class);
    }

    @Test
    @DisplayName("인증 안에 정보가 저장되어 있지 않다면 인증을 생성한다.")
    void test4() {
        UUID memberId = UUID.randomUUID();
        assertDoesNotThrow(
            () -> authenticationDetailsService.requestAuthentication(memberId)
        );
    }

    @Test
    @DisplayName("알림 정책을 통해 인증 코드 알림을 보낸다.")
    void test5() {
        UUID memberId = UUID.randomUUID();
        assertThat(authenticationDetailsService.requestAuthentication(memberId)).isTrue();
    }

    @Test
    @DisplayName("알림이 보내지지 않으면 인증에 실패한다.")
    void test6() {
        //given
        UUID memberId = NOT_INVALID_EMAIL_MEMBER_ID;
        //when & then
        assertThat(authenticationDetailsService.requestAuthentication(memberId)).isFalse();
    }

}
