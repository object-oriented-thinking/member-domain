package our.member.domain;

import our.member.error.ExpiredTokenException;
import our.member.error.InvalidTokenException;

import java.time.LocalDateTime;
import java.util.UUID;

public class Token {
    private Long id;
    private TokenVo tokenVo;
    private UserEntity userEntity;
    private TokenState tokenState;
    private LocalDateTime expiresAt;

    public Token(UUID id, String tokenVo, UserEntity userEntity, TokenState tokenState, LocalDateTime expiresAt) {
        if (!tokenState.equals(TokenState.VALID)) {
            throw new InvalidTokenException();
        }
        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new ExpiredTokenException();
        }
        this.tokenVo = new TokenVo(tokenVo);
        this.userEntity = userEntity;
        this.tokenState = tokenState;
        this.expiresAt = expiresAt;
    }
}
