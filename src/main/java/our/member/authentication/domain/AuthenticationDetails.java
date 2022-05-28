package our.member.authentication.domain;

import our.member.authentication.error.InvalidTokenException;

import java.util.UUID;

/**
 * 인증 토큰은 해당 지원자의 이메일 정보와 유효 시각 정보를 가진다.
 * 유효 시간은 등록하는 시각에서 30분이 지난 시각이다.
 */
public class AuthenticationDetails {
    private UUID id;
    private Token token;
    private UUID userId;
    private TokenState tokenState;

    public AuthenticationDetails(UUID id, String token, UUID userId, TokenState tokenState) {
        if (!tokenState.equals(TokenState.VALID)) {
            throw new InvalidTokenException();
        }
        this.token = new Token(token);
        this.userId = userId;
        this.tokenState = tokenState ;
    }

}
